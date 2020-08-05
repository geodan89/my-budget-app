package com.springexercise.mybudgetapp.service;

import com.springexercise.mybudgetapp.web.model.CategoryDto;

public interface CategoryService {

    CategoryDto getCategoryById(Long categoryId);

    CategoryDto saveNewCategory(CategoryDto categoryDto);

    CategoryDto updateCategoryDto(Long categoryId, CategoryDto categoryDto);

    void deleteById(Long categoryId);
}
