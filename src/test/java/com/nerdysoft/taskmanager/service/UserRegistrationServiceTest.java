package com.nerdysoft.taskmanager.service;

import com.nerdysoft.taskmanager.domain.User;
import com.nerdysoft.taskmanager.dto.Registrant;
import com.nerdysoft.taskmanager.exception.UserWithSuchEmailAlreadyExistsException;
import com.nerdysoft.taskmanager.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = UserRegistrationService.class)
public class UserRegistrationServiceTest {

    @Inject
    private UserRegistrationService userRegistrationService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {

    }

//    @Test
//    public void shouldRegisterNewUser() {
//        Registrant registrant = new Registrant();
//        registrant.setUsername("JohnDoe");
//        registrant.setEmail("johndoe@gmail.com");
//        registrant.setPassword("1234567");
//
//        User user = new User();
//        user.setUsername(registrant.getUsername());
//        user.setPassword("1234567");
//        user.setEmail(registrant.getEmail());
//        user.setAdmin(false);
//        user.setTasks(new ArrayList<>());
//
//        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
//        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
//        when(userRepository.save(user)).thenReturn(user);
//
//        userRegistrationService.registerNewUserAccount(registrant);
//
//    }

    @Test
    public void shouldThrowException() {
        Registrant registrant = new Registrant();
        registrant.setUsername("JohnDoe");
        registrant.setEmail("johndoe@gmail.com");
        registrant.setPassword("1234567");

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(new User()));
        // when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        // when(userRepository.save(any(User.class))).thenReturn(user);

        assertThrows(UserWithSuchEmailAlreadyExistsException.class, () -> userRegistrationService.registerNewUserAccount(registrant));
    }

}