package com.example.HouseholdAccountBook.util;

import com.example.HouseholdAccountBook.dto.CategoryDto;
import com.example.HouseholdAccountBook.dto.ExpenseDto;
import com.example.HouseholdAccountBook.dto.UserDto;
import com.example.HouseholdAccountBook.entity.Category;
import com.example.HouseholdAccountBook.entity.Expense;
import com.example.HouseholdAccountBook.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class MapperToDto {
    @Autowired
    ModelMapper modelMapper;

    public UserDto mapperToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public ExpenseDto mapperToExpenseDto(Expense expense) {
        return modelMapper.map(expense, ExpenseDto.class);
    }

    public CategoryDto mapperToCategoryDto(Category category) {
        return modelMapper.map(category, CategoryDto.class);
    }
}
