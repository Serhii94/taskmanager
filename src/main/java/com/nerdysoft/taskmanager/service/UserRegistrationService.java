package com.nerdysoft.taskmanager.service;

import com.nerdysoft.taskmanager.domain.User;
import com.nerdysoft.taskmanager.dto.Registrant;
import com.nerdysoft.taskmanager.exception.UserWithSuchEmailAlreadyExistsException;
import com.nerdysoft.taskmanager.exception.UserWithSuchUsernameAlreadyExistsException;
import com.nerdysoft.taskmanager.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserRegistrationService implements IUserRegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Inject
    public UserRegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerNewUserAccount(Registrant registrant) {

        if (usernameExists(registrant.getUsername())) {
            String message = "There is an account with such username: " + registrant.getUsername();
            throw new UserWithSuchUsernameAlreadyExistsException(message);
        }

        if (emailExists(registrant.getEmail())) {
            String message = "There is an account with such email address: " + registrant.getEmail();
            throw new UserWithSuchEmailAlreadyExistsException(message);
        }

        // The user doesn't have an id.
        User user = registrantToUser(registrant);
        // The user id is assigned after saving.
        return userRepository.save(user);
    }

    // Password encrypting goes here.
    private User registrantToUser(Registrant registrant) {
        User user = new User();
        user.setUsername(registrant.getUsername());
        user.setPassword(passwordEncoder.encode(registrant.getPassword()));
        user.setEmail(registrant.getEmail());
        user.setTasks(new ArrayList<>());
        return user;
    }

    private boolean usernameExists(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.isPresent();
    }

    private boolean emailExists(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent();
    }

}
