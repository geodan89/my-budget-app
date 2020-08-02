package com.springexercise.mybudgetapp.service;

import com.springexercise.mybudgetapp.web.model.ExpenseDto;

import java.util.List;

public interface ExpenseService {

    List<ExpenseDto> getAllExpenses();

    ExpenseDto getExpenseById(Long expenseId);

    ExpenseDto saveNewExpense(ExpenseDto expenseDto);

    ExpenseDto updateExpense(Long expenseId, ExpenseDto expenseDto);

    void deleteById(Long expenseId);
}
