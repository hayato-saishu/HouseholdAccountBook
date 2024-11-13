package com.example.HouseholdAccountBook.controller;

import com.example.HouseholdAccountBook.dto.AuthResponseDto;
import com.example.HouseholdAccountBook.dto.LoginRequest;
import com.example.HouseholdAccountBook.entity.User;
import com.example.HouseholdAccountBook.service.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthServiceImpl authService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {

        return ResponseEntity.ok(authService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequest loginRequest) {
        // token取得
        String token = authService.login(loginRequest);

        AuthResponseDto authResponseDto = new AuthResponseDto(token);

        return ResponseEntity.ok(authResponseDto);
    }
}
