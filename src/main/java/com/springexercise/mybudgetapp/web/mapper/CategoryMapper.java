package com.springexercise.mybudgetapp.web.mapper;

import com.springexercise.mybudgetapp.domain.Category;
import com.springexercise.mybudgetapp.web.model.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDto categoryToCategoryDto(Category category);

    Category categoryDtoToCategory(CategoryDto categoryDto);
}
