package com.bbi.employeeBgv.controller;

import com.bbi.employeeBgv.model.User;
import com.bbi.employeeBgv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping("/getUser")
    public List<User> fetchUser(){
        return userService.fetchUser();
    }

    @PutMapping("/update/{userId}")
    public User updateRole(@PathVariable Long userId, @RequestBody User user){
        return userService.updateRole(userId, user);
    }
}
