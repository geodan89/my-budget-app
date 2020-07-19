package com.springexercise.mybudgetapp.web.controller;

import com.springexercise.mybudgetapp.service.ExpenseService;
import com.springexercise.mybudgetapp.web.model.ExpenseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/expense")
@RestController
public class ExpenseController {

    ExpenseService expenseService;

    @GetMapping({"/{expenseId}"})
    public ResponseEntity<ExpenseDto> getExpenseById(@PathVariable Long expenseId) {
        return new ResponseEntity(expenseService.getExpenseById(expenseId), HttpStatus.OK);
    }

}
