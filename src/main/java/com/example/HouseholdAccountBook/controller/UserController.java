package com.example.HouseholdAccountBook.controller;

import com.example.HouseholdAccountBook.dto.Response;
import com.example.HouseholdAccountBook.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    /**
     * Retrieves user information based on the provided token.
     * The method validates the token, extracts the user's email,
     * and fetches the associated user details from the database.
     *
     * @param token the authentication token used to identify the user
     * @return a Response object containing the user's details or an error message
     */
    @GetMapping("/by-token")
    public Response getUserByToken(@RequestParam String token) {
        return userService.getUserByToken(token);
    }
}
