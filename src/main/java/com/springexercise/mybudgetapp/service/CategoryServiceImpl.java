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
        Category category = categoryMapper.categoryDtoToCategory(categoryDto);
        category.setCategoryName(categoryDto.getCategoryName());
        category.setInitialAmount(categoryDto.getInitialAmount());
        category.setCurrentAmount(categoryDto.getInitialAmount());
        category.setCreatedDate(categoryDto.getCreatedDate());
        Category savedCategory = categoryRepository.save(category);
        CategoryDto categoryDto1 = categoryMapper.categoryToCategoryDto(savedCategory);
        return categoryDto1;
    }

    @Override
    public CategoryDto updateCategoryDto(Long categoryId, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(NotFoundException::new);
        Double updatedInitialAmount = categoryDto.getInitialAmount();
        Double initialAmount = category.getInitialAmount();
        Double currentAmount = category.getCurrentAmount();
        Double updateDifference = updatedInitialAmount - initialAmount;
        currentAmount = currentAmount + updateDifference;
        category.setCategoryName(categoryDto.getCategoryName());
        category.setInitialAmount(categoryDto.getInitialAmount());
        category.setCurrentAmount(currentAmount);

        return categoryMapper.categoryToCategoryDto(categoryRepository.save(category));
    }

    @Override
    public void deleteById(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

}
