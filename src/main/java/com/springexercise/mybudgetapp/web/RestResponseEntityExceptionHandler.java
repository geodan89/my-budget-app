package com.springexercise.mybudgetapp.web;

import com.springexercise.mybudgetapp.service.exceptions.CategoryNotFoundException;
import com.springexercise.mybudgetapp.service.exceptions.ExpenseNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Object> handleCategoryNotFoundException(Exception exception, WebRequest request) {
        return new ResponseEntity<>("Category not found." + "\nHttp Status=" + HttpStatus.NOT_FOUND, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExpenseNotFoundException.class)
    public ResponseEntity<Object> handleExpenseNotFoundException(Exception exception, WebRequest request) {
        return new ResponseEntity<>("Expense not found." + "\nHttp Status=" + HttpStatus.NOT_FOUND, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
