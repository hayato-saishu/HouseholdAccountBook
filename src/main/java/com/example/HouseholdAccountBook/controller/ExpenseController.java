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

    @GetMapping("/by-user-id-and-month/{userId}")
    public Response getExpenseByMonth(@PathVariable String userId, @RequestParam String month) {
        return expenseService.getExpenseByUserIdAndMonth(userId, month);
    }
}
