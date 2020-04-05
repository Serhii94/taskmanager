package com.nerdysoft.taskmanager.controller;

import com.nerdysoft.taskmanager.dto.User;
import com.nerdysoft.taskmanager.dto.Registrant;
import com.nerdysoft.taskmanager.exception.ResourceNotFoundException;
import com.nerdysoft.taskmanager.repository.UserRepository;
import com.nerdysoft.taskmanager.service.IUserRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    private final UserRepository userRepository;
    private final IUserRegistrationService userService;

    @Inject
    public UserController(IUserRegistrationService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUser(@PathVariable String userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User with id" + userId + " not found.")
        );
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/users/registration")
    public ResponseEntity<User> registerNewUser(@Valid @RequestBody Registrant registrant) {
        User user = userService.registerNewUserAccount(registrant);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

}
