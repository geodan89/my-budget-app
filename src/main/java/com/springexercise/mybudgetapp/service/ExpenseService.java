package com.springexercise.mybudgetapp.service;

import com.springexercise.mybudgetapp.web.model.ExpenseDto;

import java.util.List;

public interface ExpenseService {

    List<ExpenseDto> getAllExpenses(Long categoryId);

    ExpenseDto findByCategoryIdAndExpenseId(Long categoryId, Long expenseId);

    ExpenseDto saveNewExpense(Long categoryID, ExpenseDto expenseDto);

    ExpenseDto updateExpense(Long categoryId, Long expenseId, ExpenseDto expenseDto);

    void deleteById(Long categoryId, Long expenseId);
}
