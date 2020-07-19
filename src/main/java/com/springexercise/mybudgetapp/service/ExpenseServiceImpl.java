package com.springexercise.mybudgetapp.service;

import com.springexercise.mybudgetapp.domain.Expense;
import com.springexercise.mybudgetapp.repository.ExpenseRepository;
import com.springexercise.mybudgetapp.web.controller.NotFoundException;
import com.springexercise.mybudgetapp.web.mapper.ExpenseMapper;
import com.springexercise.mybudgetapp.web.model.ExpenseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;

    @Override
    public ExpenseDto getExpenseById(Long expenseId) {
        return expenseMapper.expenseToExpenseDto(expenseRepository.findById(expenseId)
                .orElseThrow(NotFoundException::new));
    }

    @Override
    public ExpenseDto saveNewExpense(ExpenseDto expenseDto) {
        return expenseMapper.expenseToExpenseDto(expenseRepository.save(expenseMapper.expenseDtoToExpense(expenseDto)));
    }

    @Override
    public ExpenseDto updateExpense(Long expenseId, ExpenseDto expenseDto) {
        Expense expense = expenseRepository.findById(expenseId).orElseThrow(NotFoundException::new);
        expense.setName(expenseDto.getName());
        expense.setPrice(expenseDto.getPrice());
        return expenseMapper.expenseToExpenseDto(expenseRepository.save(expense));
    }

    @Override
    public void deleteById(Long expenseId) {

    }
}
