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

    /**
     * Retrieves expenses for a specific user based on the provided user ID.
     * The returned response contains a list of expenses associated with the given user ID.
     *
     * @param userId the unique identifier of the user whose expenses are to be retrieved
     * @return a Response object containing a list of expenses and a status code indicating the result of the operation
     */
    @GetMapping("/by-user-id/{userId}")
    public Response getExpenseByUserId(@PathVariable String userId) {
        return expenseService.getExpenseByUserId(userId);
    }

    /**
     * Retrieves the expenses for a specific user based on the provided user ID and month.
     * The method returns a response containing a list of expenses filtered by the specified criteria.
     *
     * @param userId the unique identifier of the user whose expenses are to be retrieved
     * @param month the month for which the expenses need to be fetched, in the format "YYYY-MM"
     * @return a Response object containing the status of the operation and the list of expenses
     */
    @GetMapping("/by-user-id-and-month/{userId}")
    public Response getExpenseByUserIdAndMonth(@PathVariable String userId, @RequestParam String month) {
        return expenseService.getExpenseByUserIdAndMonth(userId, month);
    }

    /**
     * Retrieves expenses for a specific user based on the provided user ID and category ID.
     * The method returns a response containing a list of expenses filtered by the specified criteria.
     *
     * @param userId the unique identifier of the user*/
    @GetMapping("/by-user-id-and-category-id/{userId}")
    public Response getExpenseByUserIdAndCategoryId(@PathVariable String userId, @RequestParam String categoryId) {
        return expenseService.getExpenseByUserIdAndCategoryId(userId, categoryId);
    }

    /**
     * Retrieves expenses of a specific user based on the provided user ID, category ID, and month.
     * The method returns a response containing a list of expenses filtered by the specified criteria.
     *
     * @param userId the unique identifier of the user whose expenses are to be retrieved
     * @param month the month for which the expenses need to be fetched, in*/
    @GetMapping("/by-user-id-and-category-id-and-month/{userId}")
    public Response getExpenseByUserIdAndCategoryIdAndMonth(@PathVariable String userId, @RequestParam String month, @RequestParam String categoryId) {
        return expenseService.getExpenseByUserIdAndCategoryIdAndMonth(userId, categoryId, month);
    }

    /**
     * Creates an expense record for a specific user based on the provided details.
     * The method takes the user ID, category ID, month, expense name, and amount as input
     * and returns a response indicating the status of the operation.
     *
     * @param userId the unique identifier of the user for whom the expense is being created
     * @param categoryId the unique identifier of the category to which the expense belongs
     * @param month the month associated with the expense
     * @param expenseName the name of the expense
     * @param amount the monetary amount*/
    @PostMapping("/create-expense/{userId}")
    public Response createExpense(@PathVariable String userId, @RequestParam String categoryId, @RequestParam String month, @RequestParam String expenseName, @RequestParam String amount) {
        return expenseService.createExpense(userId, categoryId, month, expenseName, amount);
    }

    /**
     * Updates an existing expense with the provided details. The expense ID corresponds
     * to the specific expense to be updated, and the other parameters define the updated values.
     *
     * @param expenseId the unique identifier of the expense to be updated
     * @param categoryId the unique identifier of the category to which the expense belongs
     * @param month the month associated with the updated expense
     * @param expenseName the updated name of the expense
     * @param amount the updated amount of the expense
     */
    @PutMapping("/update-expense/{expenseId}")
    public void updateExpense(@PathVariable String expenseId, @RequestParam String categoryId, @RequestParam String month, @RequestParam String expenseName, @RequestParam String amount) {
        expenseService.updateExpense(expenseId, categoryId, month, expenseName, amount);
    }

    /**
     * Deletes an expense based on the provided expense ID.
     * The expense ID should correspond to an existing expense in the system.
     *
     * @param expenseId the unique identifier of the expense to be deleted
     */
    @DeleteMapping("/delete-expense/{expenseId}")
    public void deleteExpense(@PathVariable String expenseId) {
        expenseService.deleteExpense(expenseId);
    }
}
