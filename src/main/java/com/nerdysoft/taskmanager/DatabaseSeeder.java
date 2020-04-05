package com.nerdysoft.taskmanager;

import com.nerdysoft.taskmanager.dto.Task;
import com.nerdysoft.taskmanager.dto.User;
import com.nerdysoft.taskmanager.repository.TaskRepository;
import com.nerdysoft.taskmanager.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;

@Service
public class DatabaseSeeder implements CommandLineRunner {

    private UserRepository userRepository;
    private TaskRepository taskRepository;
    private PasswordEncoder encoder;

    @Inject
    public DatabaseSeeder(UserRepository userRepository, TaskRepository taskRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) throws Exception {

        // drop all data from collections
        userRepository.deleteAll();
        taskRepository.deleteAll();

        // create a task
        Task task = new Task();
        task.setTitle("Special task number 1");
        task.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        task.setShares(new ArrayList<>());
        // save task in database
        task = taskRepository.save(task);

        // create a user
        User user = new User();
        user.setUsername("JohnDean");
        user.setEmail("JohnDean@gmail.com");
        user.setPassword(encoder.encode("1234567"));
        user.setTasks(new ArrayList<>());
        // Assign task to user
        user.getTasks().add(task.getId());
        // save user in database
        userRepository.save(user);

    }
}
