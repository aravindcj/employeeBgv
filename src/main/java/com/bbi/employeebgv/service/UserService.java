package com.bbi.employeebgv.service;

import com.bbi.employeebgv.model.User;
import com.bbi.employeebgv.respository.UserRepository;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @CachePut(value = "users", key = "#result.user.userId")
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Cacheable(value = "users")
    public List<User> fetchUser() {
        return userRepository.findAll();
    }

    @CachePut(value = "users", key = "#userId")
    public User updateRole(Long userId, User updatedUser) {
        Optional<User> userDetail = userRepository.findById(userId);
        if (userDetail.isPresent()) {
            User user = userDetail.get();
            user.setRole(updatedUser.getRole());
            user.setActive(updatedUser.isActive());
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            return userRepository.save(user);
        }
        return null;
    }
}
