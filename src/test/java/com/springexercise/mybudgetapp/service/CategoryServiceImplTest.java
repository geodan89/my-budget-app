package com.springexercise.mybudgetapp.service;

import com.springexercise.mybudgetapp.domain.Category;
import com.springexercise.mybudgetapp.repository.CategoryRepository;
import com.springexercise.mybudgetapp.web.mapper.CategoryMapper;
import com.springexercise.mybudgetapp.web.model.CategoryDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


class CategoryServiceImplTest {

    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(categoryRepository, categoryMapper);
    }

    @Test
    void getCategoryByIdTest() {
        //given
        Category category = new Category();
        category.setId(1L);
        category.setCategoryName("Cosmetice");
        category.setInitialAmount(250.00);

        when(categoryRepository.findById(anyLong())).thenReturn(Optional.ofNullable(category));

        //when
        CategoryDto categoryDto = categoryService.getCategoryById(1L);

        assertEquals("Cosmetice", categoryDto.getCategoryName());
        assertEquals(250.00, categoryDto.getInitialAmount());
    }

    @Test
    void getAllCategoriesTest() {
        //given
        Category category1 = new Category();
        category1.setId(1L);
        category1.setCategoryName("Carti");
        category1.setInitialAmount(200.00);

        when(categoryRepository.findAll()).thenReturn(Arrays.asList(category1, category1));

        //when
        List<CategoryDto> list = categoryService.getAllCategories();

        assertEquals(2, list.size());
    }

    @Test
    void saveNewCategoryTest() {
        //given
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryName("Haine");
        categoryDto.setInitialAmount(500.00);

        Category savedCategory = new Category();
        savedCategory.setCategoryName(categoryDto.getCategoryName());
        savedCategory.setInitialAmount(categoryDto.getInitialAmount());
        savedCategory.setId(1L);

        when(categoryRepository.save(any(Category.class))).thenReturn(savedCategory);

        //when
        CategoryDto savedDto = categoryService.saveNewCategory(categoryDto);

        //then
        assertEquals(categoryDto.getCategoryName(), savedDto.getCategoryName());
        assertEquals(categoryDto.getInitialAmount(), savedDto.getInitialAmount());
    }

    @Test
    void updateCategoryDtoTest() {
        //given
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryName("Haine");
        categoryDto.setInitialAmount(500.00);

        Category savedCategory = new Category();
        savedCategory.setCategoryName(categoryDto.getCategoryName());
        savedCategory.setInitialAmount(categoryDto.getInitialAmount());
        savedCategory.setCurrentAmount(categoryDto.getInitialAmount());
        savedCategory.setId(1L);

        when(categoryRepository.findById(anyLong())).thenReturn(Optional.ofNullable(savedCategory));
        when(categoryRepository.save(any(Category.class))).thenReturn(savedCategory);

        //when
        CategoryDto savedDto = categoryService.updateCategoryDto(anyLong(), categoryDto);

        //then
        assertEquals(categoryDto.getCategoryName(), savedDto.getCategoryName());
        assertEquals(categoryDto.getInitialAmount(), savedDto.getInitialAmount());
    }

    @Test
    void deleteByIdTest() {
        Long id = 1L;

        categoryRepository.deleteById(id);

        verify(categoryRepository, times(1)).deleteById(anyLong());

    }
}