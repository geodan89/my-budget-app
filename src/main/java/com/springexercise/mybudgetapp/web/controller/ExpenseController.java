package com.springexercise.mybudgetapp.web.controller;

import com.springexercise.mybudgetapp.service.ExpenseService;
import com.springexercise.mybudgetapp.web.model.ExpenseDto;
import com.springexercise.mybudgetapp.web.model.ExpenseListDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class ExpenseController {

    private final ExpenseService expenseService;

    /**
     * Get all expenses from a category
     **/
    @GetMapping("/category/{categoryId}/expenses")
    public ResponseEntity<ExpenseListDto> getAllExpenses(@PathVariable("categoryId") String categoryId) {
        //To be properly implemented.

        Long catId = Long.valueOf(categoryId);
        List<ExpenseDto> list = expenseService.getAllExpenses(catId);
        ExpenseListDto expenseListDto = new ExpenseListDto();
        expenseListDto.setExpenseDtoList(list);

        return new ResponseEntity<>(expenseListDto, HttpStatus.OK);
    }

    /**
     * Get a certain expense from a category
     **/
    @GetMapping("/category/{categoryId}/expense/{expenseId}")
    public ResponseEntity<ExpenseDto> getExpenseById(@PathVariable("categoryId") String categoryId, @PathVariable("expenseId") String expenseId) {
        //to be implemented;

        log.debug("I'm in controller GET method...");
        Long expId = Long.valueOf(expenseId);
        Long catId = Long.valueOf(categoryId);
        return new ResponseEntity<>(expenseService.findByCategoryIdAndExpenseId(catId, expId), HttpStatus.OK);
    }

    /**
     * Create a new expense in a category
     **/
    @PostMapping("/category/{categoryId}/expense")
    public ResponseEntity<ExpenseDto> createNewExpense(@PathVariable String categoryId, @RequestBody ExpenseDto expenseDto) {
        //To be properly implemented.

        Long catId = Long.valueOf(categoryId);
        ExpenseDto savedExpenseDto = expenseService.saveNewExpense(catId, expenseDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/api/v1/expense/" + savedExpenseDto.getId().toString());
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    /**
     * Update an expense in a category
     **/
    @PutMapping({"/category/{categoryId}/expense/{expenseId}"})
    public ResponseEntity<ExpenseDto> updateExpense(@PathVariable("categoryId") String categoryId,
                                                    @PathVariable("expenseId") String expenseId, @RequestBody @Valid ExpenseDto expenseDto) {
        //To be properly implemented.

        Long expId = Long.valueOf(expenseId);
        Long catId = Long.valueOf(categoryId);
        return new ResponseEntity<>(expenseService.updateExpense(catId, expId, expenseDto), HttpStatus.OK);
    }

//    @PatchMapping({"/expense/{expenseId}"})
//    public ResponseEntity<ExpenseDto> patchExpense(@PathVariable Long expenseId,
//                                                   @RequestBody @Valid ExpenseDto expenseDto) {
//        return new ResponseEntity<>(expenseService.updateExpense(expenseId, expenseDto), HttpStatus.OK);
//    }

    /**
     * Delete an expense from a category
     **/
    @DeleteMapping({"/category/{categoryId}/expense/{expenseId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExpenseById(@PathVariable("categoryId") String categoryId, @PathVariable("expenseId") String expenseId) {
        //To be properly implemented.
        Long expId = Long.valueOf(expenseId);
        Long catId = Long.valueOf(categoryId);
        expenseService.deleteById(catId, expId);
    }
}
