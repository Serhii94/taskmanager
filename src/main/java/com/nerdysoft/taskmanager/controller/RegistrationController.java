package com.nerdysoft.taskmanager.controller;

import com.nerdysoft.taskmanager.service.IUserRegistrationService;
import com.nerdysoft.taskmanager.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
public class RegistrationController {

    private IUserRegistrationService userService;

    @Inject
    public RegistrationController(IUserRegistrationService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/registration")
    public ResponseEntity<Void> registerNewUser(@RequestBody User user) {
        userService.registerNewUserAccount(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
