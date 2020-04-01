package com.nerdysoft.taskmanager.controller;

import com.nerdysoft.taskmanager.domain.Task;
import com.nerdysoft.taskmanager.domain.User;
import com.nerdysoft.taskmanager.dto.ShareTaskData;
import com.nerdysoft.taskmanager.dto.TaskAccess;
import com.nerdysoft.taskmanager.exception.InvalidUserEmailException;
import com.nerdysoft.taskmanager.repository.TaskRepository;
import com.nerdysoft.taskmanager.repository.UserRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.inject.Inject;
import java.net.URI;
import java.security.Principal;
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

//    @GetMapping("tasks/{taskId}")
////    public ResponseEntity<Task> getTask(@PathVariable Long taskId) {
////        Task task = taskRepository.findById(taskId).get();
////        return new ResponseEntity<>(task, HttpStatus.OK);
////    }

    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        task = taskRepository.save(task);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newTaskUri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{taskId}")
                .buildAndExpand(task.getId())
                .toUri();
        responseHeaders.setLocation(newTaskUri);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks(Authentication auth, Principal p) {
        User user = userRepository.findByUsername(p.getName()).get();
        List<Task> userTasks = (List<Task>) taskRepository.findAllById(user.getTaskIds());
        return new ResponseEntity<>(userTasks, HttpStatus.OK);
    }

    @PostMapping("/tasks/share")
    public ResponseEntity<Void> shareTask(@RequestBody ShareTaskData taskData, Principal principal) {
        String receiverEmail = taskData.getReceiverEmail();
        User receiver = userRepository.findByEmail(receiverEmail)
                .orElseThrow(() -> new InvalidUserEmailException("There wasn't found user with email: " + receiverEmail));
        receiver.getTaskIds().add(taskData.getTaskId());
        receiver.getSharedBy().add(new TaskAccess(taskData.getTaskId(), principal.getName()));
        userRepository.save(receiver);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
