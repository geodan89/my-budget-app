package com.springexercise.mybudgetapp.service;

import com.springexercise.mybudgetapp.web.model.ExpenseDto;

public interface ExpenseService {
    ExpenseDto getExpenseById(Long expenseId);

    ExpenseDto saveNewExpense(ExpenseDto expenseDto);

    ExpenseDto updateExpense(Long expenseId, ExpenseDto expenseDto);

    void deleteById(Long expenseId);
}
