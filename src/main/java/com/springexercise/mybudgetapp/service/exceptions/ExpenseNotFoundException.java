package com.springexercise.mybudgetapp.service.exceptions;

public class ExpenseNotFoundException extends RuntimeException {

    public ExpenseNotFoundException() {

    }

    public ExpenseNotFoundException(Long expenseId) {
        super("Expense not found by this expenseId=" + expenseId);
    }

}
