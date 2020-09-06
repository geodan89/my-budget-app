package com.springexercise.mybudgetapp.service;

import com.springexercise.mybudgetapp.domain.Category;
import com.springexercise.mybudgetapp.domain.Expense;
import com.springexercise.mybudgetapp.repository.CategoryRepository;
import com.springexercise.mybudgetapp.repository.ExpenseRepository;
import com.springexercise.mybudgetapp.service.exceptions.CategoryNotFoundException;
import com.springexercise.mybudgetapp.service.exceptions.ExpenseNotFoundException;
import com.springexercise.mybudgetapp.web.mapper.ExpenseMapper;
import com.springexercise.mybudgetapp.web.model.ExpenseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


class ExpenseServiceImplTest {

    private ExpenseService expenseService;

    @Mock
    private ExpenseRepository expenseRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ExpenseMapper expenseMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        expenseService = new ExpenseServiceImpl(categoryRepository, expenseRepository, expenseMapper);
    }

    @Test
    void getAllExpensesTest() {
        //given
        Expense expense1 = Expense.builder().expenseName("expense1").expensePrice(20.0).build();
        ExpenseDto expenseDto = ExpenseDto.builder().expenseName("expense1").expensePrice(20.0).build();
        Category category1 = Category.builder().categoryName("cate1").id(1L).build();
        List<Expense> expenseList = new ArrayList<>();
        expenseList.add(expense1);
        expenseList.add(expense1);
        category1.setExpenseList(expenseList);

        //when
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.ofNullable(category1));
        doReturn(expenseDto).when(expenseMapper).expenseToExpenseDto(expense1);

        List<ExpenseDto> list = expenseService.getAllExpenses(1L);

        //then
        assertEquals(2, list.size());
        assertEquals(true, list.contains(expenseDto));
    }

    @Test
    void getAllExpensesCategoryNotFoundTest() {
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> {
            expenseService.getAllExpenses(anyLong());
        });
    }

    @Test
    void findByCategoryIdAndExpenseIdTest() {
        //given
        Expense expense1 = Expense.builder().expenseName("expense1").expensePrice(20.0).id(2L).build();
        ExpenseDto expenseDto1 = ExpenseDto.builder().expenseName("expense1").expensePrice(20.0).id("2").build();
        Category category1 = Category.builder().categoryName("cate1").id(1L).build();
        List<Expense> expenseList = new ArrayList<>();
        expenseList.add(expense1);
        category1.setExpenseList(expenseList);

        //when
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.ofNullable(category1));
        when(expenseRepository.findById(anyLong())).thenReturn(Optional.ofNullable(expense1));
        doReturn(expenseDto1).when(expenseMapper).expenseToExpenseDto(expense1);

        ExpenseDto expenseDto = expenseService.findByCategoryIdAndExpenseId(1L, 2L);

        //then
        assertEquals("expense1", expenseDto.getExpenseName());
        assertEquals(20.0, expenseDto.getExpensePrice());
    }

    @Test
    void findByCategoryIdAndExpenseIdCategoryNotFoundTest() {
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(CategoryNotFoundException.class, () -> {
            expenseService.findByCategoryIdAndExpenseId(1L, 2L);
        });
    }

    @Test
    void findByCategoryIdAndExpenseIdExpenseNotFoundTest() {
        //given
        Category category1 = Category.builder().categoryName("cate1").id(1L).build();

        //when
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.ofNullable(category1));
        when(expenseRepository.findById(anyLong())).thenReturn(Optional.empty());

        //then
        assertThrows(ExpenseNotFoundException.class, () -> {
            expenseService.findByCategoryIdAndExpenseId(1L, 2L);
        });

    }

    @Test
    void saveNewExpenseTest() {
        Category category1 = Category.builder().categoryName("cate1").id(1L).build();
        ExpenseDto expenseDto1 = ExpenseDto.builder().expenseName("expense1").expensePrice(20.0).id("2").build();
        List<Expense> expenseList = new ArrayList<>();
        category1.setExpenseList(expenseList);

        Expense savedExpense = Expense.builder().expenseName(expenseDto1.getExpenseName())
                .expensePrice(expenseDto1.getExpensePrice())
                .id(2L)
                .build();
        category1.getExpenseList().add(savedExpense);
        savedExpense.setCategory(category1);

        when(categoryRepository.findById(anyLong())).thenReturn(Optional.ofNullable(category1));
        when(expenseRepository.save(any(Expense.class))).thenReturn(savedExpense);
        doReturn(savedExpense).when(expenseMapper).expenseDtoToExpense(expenseDto1);
        doReturn(expenseDto1).when(expenseMapper).expenseToExpenseDto(savedExpense);
        category1.setCurrentAmount(20.0);

        ExpenseDto expenseDto = expenseService.saveNewExpense(1L, expenseDto1);

        assertEquals(expenseDto1.getExpenseName(), expenseDto.getExpenseName());
        assertEquals(expenseDto1.getExpensePrice(), expenseDto.getExpensePrice());

    }

    @Test
    void updateExpense() {
        Category category1 = Category.builder().categoryName("cate1").id(1L).build();
        ExpenseDto expenseDto1 = ExpenseDto.builder().expenseName("expense1").expensePrice(20.0).id("2").build();
        Expense expense1 = Expense.builder().expenseName("expense1").expensePrice(20.0).id(2L).build();
        Expense savedExpense = Expense.builder().expenseName("expense1").expensePrice(20.0).id(2L).build();
        List<Expense> expenseList = new ArrayList<>();
        category1.setExpenseList(expenseList);
        category1.getExpenseList().add(expense1);

        when(categoryRepository.findById(anyLong())).thenReturn(Optional.ofNullable(category1));
        when(expenseRepository.findById(anyLong())).thenReturn(Optional.ofNullable(expense1));
        when(expenseRepository.save(any(Expense.class))).thenReturn(savedExpense);
        when(expenseMapper.expenseToExpenseDto(any(Expense.class))).thenReturn(expenseDto1);

        category1.setCurrentAmount(20.0);

        ExpenseDto expenseDto = expenseService.updateExpense(1L, 2L, expenseDto1);

        assertEquals(expenseDto.getExpenseName(), expenseDto1.getExpenseName());
        assertEquals(expenseDto.getExpensePrice(), expenseDto1.getExpensePrice());
    }

    @Test
    void deleteById() {
        Category category1 = Category.builder().categoryName("cate1").id(1L).build();
        Expense expense1 = Expense.builder().expenseName("expense1").expensePrice(20.0).id(2L).build();
        List<Expense> expenseList = new ArrayList<>();
        category1.setExpenseList(expenseList);
        category1.getExpenseList().add(expense1);
        category1.setCurrentAmount(20.0);

        when(categoryRepository.findById(anyLong())).thenReturn(Optional.ofNullable(category1));
        when(expenseRepository.findById(anyLong())).thenReturn(Optional.ofNullable(expense1));

        expenseService.deleteById(1L, anyLong());

        verify(expenseRepository, times(1)).deleteById(anyLong());
    }
}