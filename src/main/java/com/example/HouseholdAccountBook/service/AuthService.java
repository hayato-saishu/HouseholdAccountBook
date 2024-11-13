package com.example.HouseholdAccountBook.service;

import com.example.HouseholdAccountBook.dto.LoginRequest;
import com.example.HouseholdAccountBook.entity.User;

public interface AuthService {
    String login(LoginRequest loginRequest);
    User registerUser(User user);
}
