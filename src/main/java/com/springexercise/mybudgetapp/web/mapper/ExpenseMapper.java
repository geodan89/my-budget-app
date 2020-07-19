package com.springexercise.mybudgetapp.web.mapper;


import com.springexercise.mybudgetapp.domain.Expense;
import com.springexercise.mybudgetapp.web.model.ExpenseDto;
import org.mapstruct.Mapper;

@Mapper
public interface ExpenseMapper {

    ExpenseDto expenseToExpenseDto(Expense expense);

    Expense expenseDtoToExpense(ExpenseDto expenseDto);
}
