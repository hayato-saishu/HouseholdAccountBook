package com.example.HouseholdAccountBook.dto;

import lombok.Data;

import java.util.List;

@Data
public class Response {

    private int statusCode;
    private String message;
    private String accessToken;
    private UserDto userDto;
    private ExpenseDto expenseDto;
    private CategoryDto categoryDto;
    private List<UserDto> userDtoList;
    private List<ExpenseDto> expenseDtoList;
    private List<CategoryDto> categoryDtoList;
}
