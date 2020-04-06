package com.nerdysoft.taskmanager.service;

import com.nerdysoft.taskmanager.domain.User;
import com.nerdysoft.taskmanager.dto.Registrant;
import com.nerdysoft.taskmanager.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserRegistrationService implements IUserRegistrationService {

    private UserRepository userRepository;
    private PasswordEncoder bCryptPasswordEncoder;

    @Inject
    public UserRegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = passwordEncoder;
    }

    public User registerNewUserAccount(Registrant registrant) {
        User user = null;

        //TODO This is a temporary implementation. It must be reworked.
        if (isValidUsername(registrant.getUsername()) && isValidUserEmail(registrant.getEmail())) {
            user = new User();
            user.setUsername(registrant.getUsername());
            user.setPassword(bCryptPasswordEncoder.encode(registrant.getPassword()));
            user.setEmail(registrant.getEmail());
            user.setTasks(new ArrayList<>());
        }
        return userRepository.save(user);
    }

    private boolean isValidUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return false;
        }
        return true;
    }

    private boolean isValidUserEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return false;
        }
        return true;
    }

}
