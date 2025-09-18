package com.bbi.employeebgv.controller;

import com.bbi.employeebgv.config.JwtUtil;
import com.bbi.employeebgv.model.User;
import com.bbi.employeebgv.respository.UserRepository;
import com.bbi.employeebgv.service.UserDetailsServiceImpl;
import com.bbi.employeebgv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;

    public UserController(AuthenticationManager authenticationManager, JwtUtil jwtUtil,
                          UserDetailsServiceImpl userDetailsService, UserRepository userRepository,
                          BCryptPasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.get("email"), request.get("password"))
        );
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.get("email"));
        String token = jwtUtil.generateToken(userDetails.getUsername(), userDetails.getAuthorities().toString());
        return Map.of("token", token);
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
