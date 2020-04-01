package com.nerdysoft.taskmanager.service;

import com.nerdysoft.taskmanager.domain.User;
import com.nerdysoft.taskmanager.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
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

    public User registerNewUserAccount(User user) /*throws EmailExistsException, UsernameExistsException*/ {
//        if (!isValidUsername(user.getUsername())) throw new EmailExistsException();
//        if (!isValidUserEmail(user.getEmail())) throw new UsernameExistsException();

        // Make user password safe
        String userPassword = user.getPassword();
        user.setPassword(bCryptPasswordEncoder.encode(userPassword));

        return userRepository.save(user);
    }

    private boolean isValidUsername(String username) {
        Optional<User> container = userRepository.findByUsername(username);
        if (container.isPresent()) {
            return false;
        }
        return true;
    }

    private boolean isValidUserEmail(String email) {
        Optional<User> container = userRepository.findByEmail(email);
        if (container.isPresent()) {
            return false;
        }
        return true;
    }

}
