package com.springexercise.mybudgetapp.web.controller;

import com.springexercise.mybudgetapp.service.ExpenseService;
import com.springexercise.mybudgetapp.web.model.ExpenseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://my-budget-app-react-frontend.azurewebsites.net")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class ExpenseController {

    private final ExpenseService expenseService;


    @GetMapping("/category/{categoryId}/expenses")
    public ResponseEntity<List<ExpenseDto>> getAllExpenses(@PathVariable("categoryId") String categoryId) {
        Long catId = Long.valueOf(categoryId);
        List<ExpenseDto> list = expenseService.getAllExpenses(catId);
//        ExpenseListDto expenseListDto = new ExpenseListDto();
//        expenseListDto.setExpenseDtoList(list);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @GetMapping("/category/{categoryId}/expense/{expenseId}")
    public ResponseEntity<ExpenseDto> getExpenseById(@PathVariable("categoryId") String categoryId, @PathVariable("expenseId") String expenseId) {
        log.debug("I'm in controller GET method...");
        Long expId = Long.valueOf(expenseId);
        Long catId = Long.valueOf(categoryId);
        return new ResponseEntity<>(expenseService.findByCategoryIdAndExpenseId(catId, expId), HttpStatus.OK);
    }


    @PostMapping("/category/{categoryId}/expense")
    public ResponseEntity<ExpenseDto> createNewExpense(@PathVariable String categoryId, @RequestBody ExpenseDto expenseDto) {
        Long catId = Long.valueOf(categoryId);
        ExpenseDto savedExpenseDto = expenseService.saveNewExpense(catId, expenseDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/api/v1/category/" + categoryId + "/expense/" + savedExpenseDto.getId().toString());
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }


    @PutMapping({"/category/{categoryId}/expense/{expenseId}"})
    public ResponseEntity<ExpenseDto> updateExpense(@PathVariable("categoryId") String categoryId,
                                                    @PathVariable("expenseId") String expenseId, @RequestBody ExpenseDto expenseDto) {
        Long expId = Long.valueOf(expenseId);
        Long catId = Long.valueOf(categoryId);
        return new ResponseEntity<>(expenseService.updateExpense(catId, expId, expenseDto), HttpStatus.OK);
    }

    @PatchMapping({"/category/{categoryId}/expense/{expenseId}"})
    public ResponseEntity<ExpenseDto> patchExpense(@PathVariable("categoryId") String categoryId,
                                                   @PathVariable String expenseId,
                                                   @RequestBody ExpenseDto expenseDto) {
        Long catId = Long.valueOf(categoryId);
        Long expId = Long.valueOf(expenseId);

        return new ResponseEntity<>(expenseService.patchExpense(catId, expId, expenseDto), HttpStatus.OK);
    }


    @DeleteMapping({"/category/{categoryId}/expense/{expenseId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExpenseById(@PathVariable("categoryId") String categoryId, @PathVariable("expenseId") String expenseId) {
        Long expId = Long.valueOf(expenseId);
        Long catId = Long.valueOf(categoryId);
        expenseService.deleteById(catId, expId);
    }
}
