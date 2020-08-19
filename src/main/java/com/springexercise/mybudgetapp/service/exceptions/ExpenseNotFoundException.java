package com.springexercise.mybudgetapp.service.exceptions;

public class ExpenseNotFoundException extends RuntimeException {
    public ExpenseNotFoundException() {
        super("Expense not found by this expenseId.");
    }

//    public ExpenseNotFoundException (Long expenseId){
//        super("Expense not found by expenseId=" + expenseId);
//    }
}
