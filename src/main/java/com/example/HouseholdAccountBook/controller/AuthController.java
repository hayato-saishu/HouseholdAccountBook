package com.example.HouseholdAccountBook.controller;

import com.example.HouseholdAccountBook.dto.LoginResponseDto;
import com.example.HouseholdAccountBook.dto.LoginRequest;
import com.example.HouseholdAccountBook.dto.Response;
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

    @PostMapping("/signUp")
    public Response register(@RequestBody User user) {

        return authService.registerUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequest loginRequest) {
        // ログイン処理
        String token = authService.login(loginRequest);

        return ResponseEntity.ok(new LoginResponseDto(token));
    }
}
