package com.example.HouseholdAccountBook.controller;

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
    public ResponseEntity<Response> register(@RequestBody User user) {
        // 会員登録を実行
        Response response = authService.registerUser(user);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody LoginRequest loginRequest) {
        // ログイン処理
        Response response = authService.login(loginRequest);

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
