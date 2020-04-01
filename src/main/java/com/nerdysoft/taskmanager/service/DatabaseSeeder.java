package com.nerdysoft.taskmanager.service;

import com.nerdysoft.taskmanager.domain.Task;
import com.nerdysoft.taskmanager.domain.User;
import com.nerdysoft.taskmanager.repository.TaskRepository;
import com.nerdysoft.taskmanager.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Collections;

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
        ObjectId obId = new ObjectId();


        User user = new User();
        user.setUsername("JohnDean");
        user.setEmail("JohnDean@gmail.com");
        user.setPassword(encoder.encode("1234567"));
        user.setTaskIds(Collections.singletonList(obId.toString()));
        //user.setSharedBy(Collections.);

        Task task = new Task();
        task.setTitle("Special John Dean's task");
        task.setDescription("Description 1");
        task.setId(obId);

        // drop all users
        userRepository.deleteAll();

        // add user to database
        userRepository.save(user);



    }
}
