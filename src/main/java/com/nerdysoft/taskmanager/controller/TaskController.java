package com.nerdysoft.taskmanager.controller;

import com.nerdysoft.taskmanager.domain.Task;
import com.nerdysoft.taskmanager.domain.User;
import com.nerdysoft.taskmanager.dto.ShareTaskInfo;
import com.nerdysoft.taskmanager.exception.ResourceNotFoundException;
import com.nerdysoft.taskmanager.repository.TaskRepository;
import com.nerdysoft.taskmanager.repository.UserRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.inject.Inject;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TaskController {

    private TaskRepository taskRepository;
    private UserRepository userRepository;

    @Inject
    public TaskController(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/tasks/{taskId}")
    public ResponseEntity<Task> getTask(@PathVariable String taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() ->
                new ResourceNotFoundException(taskNotFoundMessage(taskId)));
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/tasks/set")
    public ResponseEntity<List<Task>> getSpecificTasks(@RequestParam(required = true) List<String> taskIds) {
        List<Task> tasks = (List<Task>) taskRepository.findAllById(taskIds);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PostMapping("/tasks")
    public ResponseEntity<User> createTask(@RequestParam(required = true) String userId,
                                           @RequestBody(required = true) Task task) {
        task.setShares(new ArrayList<>());
        task = taskRepository.save(task);
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User with id" + userId + " not found.")
        );
        user.getTasks().add(task.getId());
        userRepository.save(user);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newTaskUri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{taskId}")
                .buildAndExpand(task.getId())
                .toUri();
        responseHeaders.setLocation(newTaskUri);

        return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/tasks/{taskId}")
    public ResponseEntity<Void> updateTask(@PathVariable String taskId, @RequestBody Task task) {
        verifyTask(taskId);
        task.setId(taskId); // Just make sure that task obj has right id.
        taskRepository.save(task);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/tasks/share/{taskId}")
    public ResponseEntity<Void> shareTask(@PathVariable String taskId,
                                          @RequestParam(required = true) String providerId,
                                          @RequestParam(required = true) String receiverEmail) {
        Task task = taskRepository.findById(taskId).orElseThrow(()->
                new ResourceNotFoundException(taskNotFoundMessage(taskId))
        );
        User provider = userRepository.findById(providerId).orElseThrow(() ->
                new ResourceNotFoundException("Provider user with id " + providerId + " not found.")
        );
        User receiver = userRepository.findByEmail(receiverEmail).orElseThrow(() ->
                new ResourceNotFoundException("Receiver user with email " + receiverEmail + " not found.")
        );

        //TODO If receiver already has the task -> provider must get according notification response.
        // Make sure that receiving user already doesn't have that task.
        if (!receiver.getTasks().contains(taskId)) {
            receiver.getTasks().add(taskId);
            userRepository.save(receiver);
            task.getShares().add(new ShareTaskInfo(
                    provider.getId(),
                    provider.getUsername(),
                    receiver.getId(),
                    receiver.getUsername(),
                    LocalDateTime.now()
            ));
            taskRepository.save(task);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable String taskId, @RequestParam String userId) {
        // All users having this task.
        List<User> users = userRepository.findAllByTaskId(taskId);
        // User deleting this task.
        User user = null;
        // Only this user has this task. So, the task must be removed completely.
        if (users.size() == 1) {
            user = users.get(0);
            taskRepository.deleteById(taskId);
        }
        // Many users have this task.
        if (users.size() > 1) {
            user = users.stream().filter(u -> u.getId().equals(userId)).findFirst().get();
        }
        user.getTasks().remove(taskId);
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void verifyTask(String taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new ResourceNotFoundException(taskNotFoundMessage(taskId));
        }
    }

    private String taskNotFoundMessage(String taskId) {
        return "Task with id " + taskId + " not found.";
    }

}
