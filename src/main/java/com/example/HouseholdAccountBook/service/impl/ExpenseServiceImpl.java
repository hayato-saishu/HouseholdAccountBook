package com.example.HouseholdAccountBook.service.impl;

import com.example.HouseholdAccountBook.dto.ExpenseDto;
import com.example.HouseholdAccountBook.dto.Response;
import com.example.HouseholdAccountBook.entity.Expense;
import com.example.HouseholdAccountBook.repository.ExpenseRepository;
import com.example.HouseholdAccountBook.service.ExpenseService;
import com.example.HouseholdAccountBook.util.MapperToDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    ExpenseRepository expenseRepository;

    public Response getExpenseByUserId(String userId) {
        Response response = new Response();
        try {
            List<Expense> expenseList = expenseRepository.findByUserId(userId);
            // Dtoに詰め替え
            MapperToDto mapperToDto = new MapperToDto();
            List<ExpenseDto> expenseDtoList = new ArrayList<>();
            expenseList.forEach(expense ->
                    expenseDtoList.add(mapperToDto.mapperToExpenseDto(expense))
            );
            response.setStatusCode(200);
            response.setExpenseDtoList(expenseDtoList);
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("経費の取得に失敗しました。");
            log.warn("getExpenseByUserId error: {}", e.getMessage());
        }

        return response;
    }

    public Response getExpenseByUserIdAndMonth(String userId, String month) {
        Response response = new Response();
        try {
            List<Expense> expenses = expenseRepository.findByUserIdAndMonth(userId, month);
            // Dtoに詰め替え
            MapperToDto mapperToDto = new MapperToDto();
            List<ExpenseDto> expenseDtoList = new ArrayList<>();
            expenses.forEach(expense ->
                    expenseDtoList.add(mapperToDto.mapperToExpenseDto(expense))
            );
            response.setStatusCode(200);
            response.setExpenseDtoList(expenseDtoList);
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("経費の取得に失敗しました。");
            log.warn("getExpenseByUserIdAndMonth error: {}", e.getMessage());
        }

        return response;
    }

    public Response getExpenseByUserIdAndCategoryId(String userId, String categoryId) {
        Response response = new Response();
        try {
            List<Expense> expenses = expenseRepository.findByUserIdAndCategoryId(userId, categoryId);
            // Dtoに詰め替え
            MapperToDto mapperToDto = new MapperToDto();
            List<ExpenseDto> expenseDtoList = new ArrayList<>();
            expenses.forEach(expense ->
                    expenseDtoList.add(mapperToDto.mapperToExpenseDto(expense))
            );
            response.setExpenseDtoList(expenseDtoList);
            response.setStatusCode(200);
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("経費の取得に失敗しました。");
            log.warn("getExpenseByUserIdAndCategoryId error: {}", e.getMessage());
        }
        return response;
    }

    public Response getExpenseByUserIdAndCategoryIdAndMonth(String userId, String categoryId, String month) {
        Response response = new Response();
        try {
            List<Expense> expenses = expenseRepository.findByUserIdAndCategoryIdAndMonth(userId, categoryId, month);
            // Dtoに詰め替え
            MapperToDto mapperToDto = new MapperToDto();
            List<ExpenseDto> expenseDtoList = new ArrayList<>();
            expenses.forEach(expense ->
                    expenseDtoList.add(mapperToDto.mapperToExpenseDto(expense))
            );
            response.setExpenseDtoList(expenseDtoList);
            response.setStatusCode(200);
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("経費の取得に失敗しました。");
            log.warn("getExpenseByUserIdAndCategoryIdAndMonth error: {}", e.getMessage());
        }
        return response;
    }

    public Response createExpense(String userId, String categoryId, String month, String expenseName, String amount) {
        Response response = new Response();
        try {
            // Create Expense object
            Expense expense = Expense.builder()
                    .userId(userId)
                    .categoryId(categoryId)
                    .month(month)
                    .expenseName(expenseName)
                    .amount(Integer.parseInt(amount))
                    .build();

            // Save expense
            expenseRepository.save(expense);

            // Set success response
            response.setStatusCode(200);
            response.setMessage("経費が正常に保存されました。");
        } catch (Exception e) {
            // Set error response
            response.setStatusCode(500);
            response.setMessage("経費の保存に失敗しました。");
            log.warn("createExpense error: {}", e.getMessage());
        }
        return response;
    }

    public void updateExpense(String expenseId, String categoryId, String month, String expenseName, String amount) {
        Response response = new Response();
        try {
            // Find the expense by expenseId
            Expense expense = expenseRepository.findById(expenseId).orElse(null);

            // If expense does not exist, return error response
            if (expense == null) {
                response.setStatusCode(404);
                response.setMessage("経費が見つかりませんでした。");
                return;
            }

            // Update fields
            expense.setCategoryId(categoryId);
            expense.setMonth(month);
            expense.setExpenseName(expenseName);
            expense.setAmount(Integer.parseInt(amount));

            // Save updated expense
            expenseRepository.save(expense);

            // Set success response
            response.setStatusCode(200);
            response.setMessage("経費が正常に更新されました。");
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("経費の更新に失敗しました。");
            log.warn("updateExpense error: {}", e.getMessage());
        }
    }

    public void deleteExpense(String expenseId) {
        Response response = new Response();
        try {
            // Delete the expense by id
            expenseRepository.deleteById(expenseId);
            response.setStatusCode(200);
            response.setMessage("経費が正常に削除されました。");
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("経費の削除に失敗しました。");
            log.warn("deleteExpense error: {}", e.getMessage());
        }
    }
}
