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

    @GetMapping("/by-token")
    public Response getUserByToken(@RequestParam String token) {
        return userService.getUserByToken(token);
    }
}
