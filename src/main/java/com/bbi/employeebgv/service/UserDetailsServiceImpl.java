package com.bbi.employeebgv.service;

import com.bbi.employeebgv.config.CustomUserDetails;
import com.bbi.employeebgv.respository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.bbi.employeebgv.model.User user = userRepository.findByEmail(email).orElseThrow(() ->new UsernameNotFoundException("User not found with email: " + email));
        return new CustomUserDetails(user); // Convert to Spring Security format
    }
}

