package com.example.zhangirakzhan.service;

import com.example.zhangirakzhan.entity.User;

import java.util.Optional;
import java.util.stream.Stream;

public interface IUserService {
    public void createUser(User user);

    public void deleteUser(User user);

    public Optional<User> getUserById(Long id);
    public Stream<User> findAll();

    public String findEmailById(Long id);
    public User findByFirstName(String firstName);
    public User findByLastName(String lastName);

    public User findByUsername(String username);

    public User updateUsername(String oldUsername, String newUsername);
    public Boolean isUsernameTaken(String username);

    public void updatePassword(Long userId, String newPassword);
    
}
