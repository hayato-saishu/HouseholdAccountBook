package com.example.HouseholdAccountBook.controller;

import com.example.HouseholdAccountBook.dto.LoginRequest;
import com.example.HouseholdAccountBook.dto.Response;
import com.example.HouseholdAccountBook.entity.User;
import com.example.HouseholdAccountBook.service.impl.AuthServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthServiceImpl authService;

    /**
     * Registers a new user in the system.
     * The method processes the provided user object and attempts to create a new account.
     * Returns a response indicating the success or failure of the operation.
     *
     * @param user the user object containing the details needed for registration, including name, email, and password
     * @return a ResponseEntity containing a Response object with the status code and message of the registration process
     */
    @PostMapping("/signUp")
    public ResponseEntity<Response> register(@RequestBody User user) {
        log.info("Registering user: {}", user);
        // 会員登録を実行
        Response response = authService.registerUser(user);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    /**
     * Authenticates a user based on the provided login details.
     * The method processes the login request, validates the user credentials,
     * and returns a response with the status of the operation, including an access token if successful.
     *
     * @param loginRequest the LoginRequest object containing the user's email and password for authentication
     * @return a ResponseEntity containing a Response object with the authentication result, status code, and additional details such as access token or error message
     */
    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody LoginRequest loginRequest) {
        log.info("Logging in user: {}", loginRequest);
        // ログイン処理
        Response response = authService.login(loginRequest);

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
