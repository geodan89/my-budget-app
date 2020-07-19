package com.springexercise.mybudgetapp.web.controller;

import com.springexercise.mybudgetapp.service.ExpenseService;
import com.springexercise.mybudgetapp.web.model.ExpenseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/expense")
@RestController
public class ExpenseController {

    ExpenseService expenseService;

    @GetMapping({"/{expenseId}"})
    public ResponseEntity<ExpenseDto> getExpenseById(@PathVariable Long expenseId) {
        return new ResponseEntity<ExpenseDto>(expenseService.getExpenseById(expenseId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ExpenseDto> createNewExpense(@RequestBody ExpenseDto expenseDto) {
        return new ResponseEntity<ExpenseDto>(expenseService.createNewExpense(expenseDto), HttpStatus.CREATED);
    }

    @PutMapping("/{expenseId}")
    public ResponseEntity<ExpenseDto> updateExpense(@PathVariable Long expenseId,
                                                    @RequestBody @Valid ExpenseDto expenseDto) {
        return new ResponseEntity<ExpenseDto>(expenseService.updateExpense(expenseId, expenseDto), HttpStatus.OK);
    }
}
