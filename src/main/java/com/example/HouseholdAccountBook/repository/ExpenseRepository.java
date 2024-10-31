package com.example.HouseholdAccountBook.repository;

import com.example.HouseholdAccountBook.entity.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ExpenseRepository extends MongoRepository<Expense, String> {

    List<Expense> findByUserId(String userId);

    List<Expense> findByUserIdAndMonth(String userId, String month);

    List<Expense> findByUserIdAndCategoryId(String userId, String categoryId);

    List<Expense> findByUserIdAndCategoryIdAndMonth(String userId, String categoryId, String month);
}
