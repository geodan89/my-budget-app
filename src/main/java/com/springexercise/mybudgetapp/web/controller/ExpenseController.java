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

    @GetMapping("/expenses")
    public ResponseEntity<ExpenseListDto> getAllExpenses() {
        List<ExpenseDto> list = expenseService.getAllExpenses();
        ExpenseListDto expenseListDto = new ExpenseListDto();
        expenseListDto.setExpenseDtoList(list);

        return new ResponseEntity<>(expenseListDto, HttpStatus.OK);
    }

    @GetMapping("/expense/{expenseId}")
    public ResponseEntity<ExpenseDto> getExpenseById(@PathVariable String expenseId) {
        log.debug("I'm in controller GET method...");
        Long expId = Long.valueOf(expenseId);
        return new ResponseEntity<>(expenseService.getExpenseById(expId), HttpStatus.OK);
    }

    @PostMapping("/expense")
    public ResponseEntity<ExpenseDto> createNewExpense(@RequestBody ExpenseDto expenseDto) {
        ExpenseDto savedExpenseDto = expenseService.saveNewExpense(expenseDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/api/v1/expense/" + savedExpenseDto.getId().toString());
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping({"/expense/{expenseId}"})
    public ResponseEntity<ExpenseDto> updateExpense(@PathVariable String expenseId,
                                                    @RequestBody @Valid ExpenseDto expenseDto) {
        Long expId = Long.valueOf(expenseId);
        return new ResponseEntity<>(expenseService.updateExpense(expId, expenseDto), HttpStatus.OK);
    }

//    @PatchMapping({"/expense/{expenseId}"})
//    public ResponseEntity<ExpenseDto> patchExpense(@PathVariable Long expenseId,
//                                                   @RequestBody @Valid ExpenseDto expenseDto) {
//        return new ResponseEntity<>(expenseService.updateExpense(expenseId, expenseDto), HttpStatus.OK);
//    }

    @DeleteMapping({"/expense/{expenseId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExpense(@PathVariable String expenseId) {
        Long expId = Long.valueOf(expenseId);
        expenseService.deleteById(expId);
    }
}
