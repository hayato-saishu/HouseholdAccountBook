package com.example.HouseholdAccountBook.service;

import com.example.HouseholdAccountBook.dto.Response;

public interface UserService {
    Response getUserByToken(String token);
}
