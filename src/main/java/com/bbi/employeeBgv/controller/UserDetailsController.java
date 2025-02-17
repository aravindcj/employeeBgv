package com.bbi.employeeBgv.controller;

import com.bbi.employeeBgv.model.UserDetails;
import com.bbi.employeeBgv.dto.UserDetailsRequest;
import com.bbi.employeeBgv.service.UserDetailsService;
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
