package com.example.HouseholdAccountBook.service;

import com.example.HouseholdAccountBook.dto.Response;

public interface ExpenseService {
    Response getExpenseByUserId(String userId);
    Response getExpenseByUserIdAndMonth(String userId, String month);
    Response getExpenseByUserIdAndCategoryId(String userId, String categoryId);
    Response getExpenseByUserIdAndCategoryIdAndMonth(String userId, String categoryId, String month);
    Response createExpense(String userId, String categoryId, String month, String expenseName, String amount);
    void updateExpense(String expenseId, String categoryId, String month, String expenseName, String amount);
    void deleteExpense(String expenseId);
}
