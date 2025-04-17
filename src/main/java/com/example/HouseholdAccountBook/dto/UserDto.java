package com.example.HouseholdAccountBook.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {
    private String id;
    private String name;
    private String password;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
