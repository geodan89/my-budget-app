package com.springexercise.mybudgetapp.service;

import com.springexercise.mybudgetapp.domain.Category;
import com.springexercise.mybudgetapp.domain.Expense;
import com.springexercise.mybudgetapp.repository.CategoryRepository;
import com.springexercise.mybudgetapp.repository.ExpenseRepository;
import com.springexercise.mybudgetapp.service.exceptions.CategoryNotFoundException;
import com.springexercise.mybudgetapp.service.exceptions.ExpenseNotFoundException;
import com.springexercise.mybudgetapp.web.controller.NotFoundException;
import com.springexercise.mybudgetapp.web.mapper.ExpenseMapper;
import com.springexercise.mybudgetapp.web.model.ExpenseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final CategoryRepository categoryRepository;
    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;


    @Override
    public List<ExpenseDto> getAllExpenses(Long categoryId) {
        Category category = getCategoryById(categoryId);
        List<ExpenseDto> list = category.getExpenseList().stream()
                .map(expenseMapper::expenseToExpenseDto)
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public ExpenseDto findByCategoryIdAndExpenseId(Long categoryId, Long expenseId) {
        Category category = getCategoryById(categoryId);
        Expense expense = expenseRepository.findById(expenseId).orElseThrow(ExpenseNotFoundException::new);
        if (!expense.getCategory().getId().equals(category.getId())) {
            throw new CategoryNotFoundException(categoryId);
        }
        ExpenseDto expenseDto = expenseMapper.expenseToExpenseDto(expense);
        return expenseDto;
    }

    @Override
    public ExpenseDto saveNewExpense(Long categoryId, ExpenseDto expenseDto) {
        Category category = getCategoryById(categoryId);
        Expense expense = expenseMapper.expenseDtoToExpense(expenseDto);
        expense.setExpenseName(expenseDto.getExpenseName());
        expense.setExpensePrice(expenseDto.getExpensePrice());
        expense.setCreatedDate(expenseDto.getCreatedDate());
        expense.setCategory(category);
        category.getExpenseList().add(expense);
        Double currentAmount = category.getCurrentAmount();
        category.setCurrentAmount(currentAmount - expenseDto.getExpensePrice());
        Expense savedExpense = expenseRepository.save(expense);
        ExpenseDto savedExpenseDto = expenseMapper.expenseToExpenseDto(savedExpense);

        return savedExpenseDto;
    }

    @Override
    public ExpenseDto updateExpense(Long categoryId, Long expenseId, ExpenseDto expenseDto) {
        Category category = getCategoryById(categoryId);
        Optional<Expense> expenseOptional = category.getExpenseList().stream()
                .filter(expense -> expense.getId().equals(expenseId))
                .findFirst();
        if (!expenseOptional.isPresent()) {
            throw new NotFoundException();
        } else {
            Expense expenseFound = expenseRepository.findById(expenseId).orElseThrow(NotFoundException::new);
            //expenseFound.setId(expenseId);
            expenseFound.setExpenseName(expenseDto.getExpenseName());
            Double diff = expenseFound.getExpensePrice() - expenseDto.getExpensePrice();
            expenseFound.setExpensePrice(expenseDto.getExpensePrice());
            category.setCurrentAmount(category.getCurrentAmount() + diff);
            Expense saveExpense = expenseRepository.save(expenseFound);
            return expenseMapper.expenseToExpenseDto(saveExpense);
        }
    }

    @Override
    public void deleteById(Long categoryId, Long expenseId) {
        Category category = getCategoryById(categoryId);
        Optional<Expense> expenseOptional = category.getExpenseList().stream()
                .filter(expense -> expense.getId().equals(expenseId))
                .findFirst();
        if (!expenseOptional.isPresent()) {
            throw new ExpenseNotFoundException();
        }
        Expense expense = expenseOptional.get();
        category.getExpenseList().remove(expense);
        category.setCurrentAmount(category.getCurrentAmount() + expense.getExpensePrice());
        expenseRepository.deleteById(expenseId);
    }

    private Category getCategoryById(Long categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (!categoryOptional.isPresent()) {
            log.debug("category not found by Id by categoryId=" + categoryId);
            throw new CategoryNotFoundException(categoryId);
        }
        Category category = categoryOptional.get();
        return category;
    }
}
