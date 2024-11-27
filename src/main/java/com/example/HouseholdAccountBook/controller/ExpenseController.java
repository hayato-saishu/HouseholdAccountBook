package com.example.HouseholdAccountBook.controller;

import com.example.HouseholdAccountBook.dto.Response;
import com.example.HouseholdAccountBook.service.impl.ExpenseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {

    @Autowired
    ExpenseServiceImpl expenseService;

    @GetMapping("/by-user-id/{userId}")
    public Response getExpenseByUserId(@PathVariable String userId) {
        return expenseService.getExpenseByUserId(userId);
    }

    @GetMapping("/by-user-id-and-month/{userId}")
    public Response getExpenseByUserIdAndMonth(@PathVariable String userId, @RequestParam String month) {
        return expenseService.getExpenseByUserIdAndMonth(userId, month);
    }

    @GetMapping("/by-user-id-and-category-id/{userId}")
    public Response getExpenseByUserIdAndCategoryId(@PathVariable String userId, @RequestParam String categoryId) {
        return expenseService.getExpenseByUserIdAndCategoryId(userId, categoryId);
    }

    @GetMapping("/by-user-id-and-category-id-and-month/{userId}")
    public Response getExpenseByUserIdAndCategoryIdAndMonth(@PathVariable String userId, @RequestParam String month, @RequestParam String categoryId) {
        return expenseService.getExpenseByUserIdAndCategoryIdAndMonth(userId, categoryId, month);
    }
}
