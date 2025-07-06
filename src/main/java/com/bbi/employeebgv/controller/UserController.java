package com.bbi.employeebgv.controller;

import com.bbi.employeebgv.model.User;
import com.bbi.employeebgv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/getUser")
    public ResponseEntity<List<User>> fetchUser(){
        return ResponseEntity.ok(userService.fetchUser());
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<User> updateRole(@PathVariable Long userId, @RequestBody User user){
        return ResponseEntity.ok(userService.updateRole(userId, user));
    }
}
