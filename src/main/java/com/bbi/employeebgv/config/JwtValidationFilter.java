package com.bbi.employeebgv.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtValidationFilter extends OncePerRequestFilter {

    JwtUtil jwtutil;
    public JwtValidationFilter(){}
    public JwtValidationFilter(JwtUtil jwtutil){
        this.jwtutil = jwtutil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or missing token");
            return;
        }

        String token = authorizationHeader.substring(7); // Remove "Bearer "
        String username = jwtutil.extractUsername(token);

        System.out.println("Valid token for user: " + username);

        filterChain.doFilter(request, response);
    }
}
