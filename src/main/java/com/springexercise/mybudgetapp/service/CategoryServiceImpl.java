package com.springexercise.mybudgetapp.service;

import com.springexercise.mybudgetapp.domain.Category;
import com.springexercise.mybudgetapp.repository.CategoryRepository;
import com.springexercise.mybudgetapp.web.controller.NotFoundException;
import com.springexercise.mybudgetapp.web.mapper.CategoryMapper;
import com.springexercise.mybudgetapp.web.model.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        return categoryMapper.categoryToCategoryDto(categoryRepository.findById(categoryId)
                .orElseThrow(NotFoundException::new));
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<CategoryDto> list = categoryRepository.findAll()
                .stream()
                .map(categoryMapper::categoryToCategoryDto)
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public CategoryDto saveNewCategory(CategoryDto categoryDto) {
        return categoryMapper.categoryToCategoryDto(categoryRepository.save(categoryMapper.categoryDtoToCategory(categoryDto)));
    }

    @Override
    public CategoryDto updateCategoryDto(Long categoryId, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(NotFoundException::new);
        category.setName(categoryDto.getCategoryName());
        category.setCategoryAmount(categoryDto.getCategoryAmount());

        return categoryMapper.categoryToCategoryDto(categoryRepository.save(category));
    }

    @Override
    public void deleteById(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
