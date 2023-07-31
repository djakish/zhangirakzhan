package com.example.zhangirakzhan.service;


import com.example.zhangirakzhan.entity.User;
import com.example.zhangirakzhan.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

import java.util.stream.Stream;


@Service
@Repository
@Transactional
public class UserService implements IUserService{
    private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);
        LOGGER.info("User created");
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return  userRepository.findById(id);
    }

    @Override
    public Stream<User> findAll() {
        return userRepository.findAll().stream();
    }

    @Override
    public String findEmailById(Long id) {
        return userRepository.findById(id).map(User::getEmail).orElse(null);
    }

    @Override
    public User findByFirstName(String firstName) {
        return userRepository.findAll()
                .stream()
                .filter(u -> u.getFirstName().equals(firstName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public User findByLastName(String lastName) {
        return userRepository.findAll()
                .stream()
                .filter(u -> u.getLastName().equals(lastName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository
                .findAll()
                .stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(new User());
    }

    @Override
    public User updateUsername(String oldUsername, String newUsername) {
        var user = userRepository
                    .findAll()
                    .stream()
                    .filter(u -> u.getUsername().equals(oldUsername))
                    .findFirst()
                    .get();

        if(isUsernameTaken(oldUsername)){
            user.setUsername(newUsername);
        }
        return  user;
    }

    @Override
    public Boolean isUsernameTaken(String username) {
        Boolean found = userRepository
                .findAll()
                .stream()
                .filter(u -> u.getUsername().equals(username))
                .findAny()
                .isPresent();
        return found;
    }

    @Override
    public Boolean isUserAdmin(Long id) {
        User user = userRepository.findById(id).get();
        if(Objects.equals(user.getRole(), "ADMIN")){
            return true;
        }
        return false;
    }

    @Override
    public void updatePassword(Long userId, String newPassword) {
        userRepository.findById(userId).ifPresent(user -> user.setPassword(newPassword));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findAll()
                .stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
}
