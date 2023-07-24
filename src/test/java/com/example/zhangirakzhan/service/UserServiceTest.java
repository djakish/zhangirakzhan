package com.example.zhangirakzhan.service;

import com.example.zhangirakzhan.entity.User;
import com.example.zhangirakzhan.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;


class UserServiceTest {
    @Mock
    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceTest.class);

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        User user = new User();
        user.setId(1L);
        user.setEmail("john.doe@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPassword("123");
        user.setUsername("john");

        userRepository.save(user);
        userRepository.findAll().stream().forEach(u -> LOGGER.info(u.getUsername()));
    }

    @Test
    void testCreateUser() {
        User user = new User();
        userService.createUser(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testDeleteUser() {
        userService.deleteUser(new User());
    }

    @Test
    void testGetUserById() {
        User user = new User();
        user.setId(1L);
        user.setEmail("john.doe@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPassword("123");
        user.setUsername("john");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserById(1L);

        Assertions.assertEquals(user, result.get());
    }

    @Test
    void testFindAll() {
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        when(userRepository.findAll()).thenReturn(userList);

        Stream<User> result = userService.findAll();
        Assertions.assertEquals( userList , result.collect(Collectors.toList()));
    }

    @Test
    void testFindEmailById() {
        User user = new User();
        user.setId(1L);
        user.setEmail("john.doe@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPassword("123");
        user.setUsername("john");

        List<User> userList = new ArrayList<>();
        userList.add(user);
        when(userRepository.findAll()).thenReturn(userList);

        String result = userService.findEmailById(1L);
        Assertions.assertEquals("john.doe@example.com", result);
    }

    @Test
    void testFindByFirstName() {
        String firstName = "John";
        User user = new User();
        user.setFirstName(firstName);
        List<User> userList = new ArrayList<>();
        userList.add(user);
        when(userRepository.findAll()).thenReturn(userList);

        User result = userService.findByFirstName(firstName);

        Assertions.assertEquals(user, result);
    }

    @Test
    void testFindByLastName() {
        String lastName = "Doe";
        User user = new User();
        user.setLastName(lastName);
        List<User> userList = new ArrayList<>();
        userList.add(user);
        when(userRepository.findAll()).thenReturn(userList);

        User result = userService.findByLastName(lastName);

        Assertions.assertEquals(user, result);
    }

    @Test
    void testFindByUsername() {
        User user = new User();
        user.setId(1L);
        user.setEmail("john.doe@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPassword("123");
        user.setUsername("john");

        List<User> userList = new ArrayList<>();
        userList.add(user);
        when(userRepository.findAll()).thenReturn(userList);

        User result = userService.findByUsername("john");
        Assertions.assertEquals(user, result);
    }

    @Test
    void testUpdateUsername() {
        String oldUsername = "oldUsername";
        String newUsername = "newUsername";

        User user = new User();
        user.setUsername(oldUsername);

        List<User> userList = new ArrayList<>();
        userList.add(user);
        when(userRepository.findAll()).thenReturn(userList);

        User result = userService.updateUsername(oldUsername, newUsername);

        Assertions.assertEquals(newUsername, result.getUsername());
    }

    @Test
    void testIsUsernameTaken() {
        String username = "john";
        User user = new User();
        user.setUsername(username);
        List<User> userList = new ArrayList<>();
        userList.add(user);
        when(userRepository.findAll()).thenReturn(userList);

        boolean result = userService.isUsernameTaken(username);

        Assertions.assertEquals(Boolean.TRUE, result);
    }

    @Test
    void testUpdatePassword() {
        Long userId = 1L;
        String newPassword = "newPassword";
        User user = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        userService.updatePassword(userId, newPassword);

        Assertions.assertEquals(newPassword, user.getPassword());
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme