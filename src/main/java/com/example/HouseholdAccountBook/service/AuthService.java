package com.example.HouseholdAccountBook.service;

import com.example.HouseholdAccountBook.dto.LoginRequest;
import com.example.HouseholdAccountBook.dto.Response;
import com.example.HouseholdAccountBook.entity.User;

public interface AuthService {
    Response login(LoginRequest loginRequest);
    Response registerUser(User user);
}
