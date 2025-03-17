package com.bbi.employeebgv.controller;

import com.bbi.employeebgv.model.UserDetails;
import com.bbi.employeebgv.dto.UserDetailsRequest;
import com.bbi.employeebgv.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userDetails")
public class UserDetailsController {

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/addUserDetails")
    public UserDetails createUserDetails(@RequestBody UserDetailsRequest userDetails){

        return userDetailsService.createUserDetails(userDetails);
    }

    @GetMapping
    public List<UserDetails> getAllUserDetails(){
        return userDetailsService.getAllUserDetails();
    }

}
