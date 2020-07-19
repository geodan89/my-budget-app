package com.springexercise.mybudgetapp.web.mapper;

import com.springexercise.mybudgetapp.domain.Category;
import com.springexercise.mybudgetapp.web.model.CategoryDto;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryMapper {

    CategoryDto categoryToCategoryDto(Category category);

    Category categoryDtoToCategory(CategoryDto categoryDto);
}
