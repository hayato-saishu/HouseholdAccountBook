package com.example.HouseholdAccountBook.service.impl;

import com.example.HouseholdAccountBook.dto.ExpenseDto;
import com.example.HouseholdAccountBook.dto.Response;
import com.example.HouseholdAccountBook.entity.Expense;
import com.example.HouseholdAccountBook.repository.ExpenseRepository;
import com.example.HouseholdAccountBook.service.ExpenseService;
import com.example.HouseholdAccountBook.util.MapperToDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    ExpenseRepository expenseRepository;

    public Response getExpenseByUserIdAndMonth(String userId, String month) {
        Response response = new Response();
        try {
            List<Expense> expenses = expenseRepository.findByUserIdAndMonth(userId, month);
            // Dtoに詰め替え
            MapperToDto mapperToDto = new MapperToDto();
            List<ExpenseDto> expenseDtoList = new ArrayList<>();
            for(Expense expense: expenses) {
                expenseDtoList.add(mapperToDto.mapperToExpenseDto(expense));
            }
            response.setStatusCode(200);
            response.setExpenseDtoList(expenseDtoList);
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("経費の取得に失敗しました。");
        }

        return response;
    }

    public Response getExpenseByUserIdAndCategoryId(String userId, String categoryId) {
        return null;
    }

    public Response getByUserIdAndCategoryIdAndMonth(String userId, String categoryId, String month) {
        return null;
    }
}
