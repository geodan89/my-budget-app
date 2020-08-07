package com.springexercise.mybudgetapp.service;

import com.springexercise.mybudgetapp.domain.Category;
import com.springexercise.mybudgetapp.domain.Expense;
import com.springexercise.mybudgetapp.repository.CategoryRepository;
import com.springexercise.mybudgetapp.repository.ExpenseRepository;
import com.springexercise.mybudgetapp.web.mapper.ExpenseMapper;
import com.springexercise.mybudgetapp.web.model.ExpenseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;
    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;


    @Override
    public List<ExpenseDto> getAllExpenses(Long categoryId) {
        //to be implemented;
        return null;
    }

    @Override
    public ExpenseDto findByCategoryIdAndExpenseId(Long categoryId, Long expenseId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (!categoryOptional.isPresent()) {
            log.debug("category not found by Id. categoryId=" + categoryId);
        }

        Category category = categoryOptional.get();
        List<Expense> expenseList = category.getExpenseList();
        Optional<ExpenseDto> expenseDtoOptional = Optional.empty();
        for (Expense expense : expenseList) {
            if (expense.getId().equals(expenseId)) {
                expenseDtoOptional = Optional.ofNullable(expenseMapper.expenseToExpenseDto(expense));
            }
        }
        if (!expenseDtoOptional.isPresent()) {
            log.error("expense not found by Id. expenseId=" + expenseId);
        }
        return expenseDtoOptional.get();
    }

    @Override
    public ExpenseDto saveNewExpense(Long categoryID, ExpenseDto expenseDto) {
        //to be implemented;
        return null;
    }

    @Override
    public ExpenseDto updateExpense(Long categoryId, Long expenseId, ExpenseDto expenseDto) {
        //to be implemented;
        return null;
    }

    @Override
    public void deleteById(Long categoryId, Long expenseId) {
        //to be implemented;
    }

}
