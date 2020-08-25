package com.springexercise.mybudgetapp.service.exceptions;


public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException() {
    }

    public CategoryNotFoundException(Long categoryId) {
        super("Category not found by categoryId=" + categoryId);
    }
}
