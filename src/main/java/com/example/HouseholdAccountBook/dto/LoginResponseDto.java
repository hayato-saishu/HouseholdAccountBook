package com.example.HouseholdAccountBook.dto;

import lombok.Data;

@Data
public class LoginResponseDto {
    private String accessToken;

    public LoginResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
