package com.example.HouseholdAccountBook.service;

import com.example.HouseholdAccountBook.dto.Response;

public interface ExpenseService {
    Response getExpenseByUserId(String userId);
    Response getExpenseByUserIdAndMonth(String userId, String month);
    Response getExpenseByUserIdAndCategoryId(String userId, String categoryId);
    Response getExpenseByUserIdAndCategoryIdAndMonth(String userId, String categoryId, String month);
}
