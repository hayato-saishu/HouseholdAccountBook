package com.example.HouseholdAccountBook.dto;

import com.example.HouseholdAccountBook.entity.Category;
import com.example.HouseholdAccountBook.entity.Expense;
import com.example.HouseholdAccountBook.entity.User;
import lombok.Data;

@Data
public class Response {

    private int statusCode;
    private String message;
    private UserDto userDto;
    private ExpenseDto expenseDto;
    private CategoryDto categoryDto;
}
