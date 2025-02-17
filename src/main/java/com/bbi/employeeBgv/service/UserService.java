package com.bbi.employeeBgv.service;

import com.bbi.employeeBgv.model.User;
import com.bbi.employeeBgv.respository.UserRepository;
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

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> fetchUser() {
        return userRepository.findAll();
    }

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
