package com.example.HouseholdAccountBook.service.impl;

import com.example.HouseholdAccountBook.Exception.OurException;
import com.example.HouseholdAccountBook.dto.LoginRequest;
import com.example.HouseholdAccountBook.entity.User;
import com.example.HouseholdAccountBook.repository.UserRepository;
import com.example.HouseholdAccountBook.util.JWTUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtils jwtUtils;

    public User registerUser(@Valid User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new OurException("メールアドレスが既に使われています。");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public String login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtUtils.generateToken(authentication);
    }
}
