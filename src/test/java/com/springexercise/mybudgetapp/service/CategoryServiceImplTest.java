package com.springexercise.mybudgetapp.service;

import com.springexercise.mybudgetapp.domain.Category;
import com.springexercise.mybudgetapp.repository.CategoryRepository;
import com.springexercise.mybudgetapp.service.exceptions.CategoryNotFoundException;
import com.springexercise.mybudgetapp.web.mapper.CategoryMapper;
import com.springexercise.mybudgetapp.web.model.CategoryDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


class CategoryServiceImplTest {

    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    private CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

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
    void getCategoryByIdCategoryNotFoundTest() {
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> {
            categoryService.getCategoryById(anyLong());
        });
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

        when(categoryRepository.findById(anyLong())).thenReturn(Optional.ofNullable(savedCategory));
        when(categoryRepository.save(any(Category.class))).thenReturn(savedCategory);

        //when
        CategoryDto savedDto = categoryService.updateCategoryDto(anyLong(), categoryDto);

        //then
        assertEquals(categoryDto.getCategoryName(), savedDto.getCategoryName());
        assertEquals(categoryDto.getInitialAmount(), savedDto.getInitialAmount());
    }

    @Test
    void updateCategoryNotFoundTest() {
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.empty());

        CategoryDto categoryDto = new CategoryDto();
        assertThrows(CategoryNotFoundException.class, () -> {
            categoryService.updateCategoryDto(anyLong(), categoryDto);
        });
    }

    @Test
    void patchCategoryDtoTest() {
        CategoryDto categoryDto = CategoryDto.builder().id("1").categoryName("Haine").build();
        Category category = Category.builder()
                .id(1L)
                .categoryName("Garderoba")
                .expenseList(new ArrayList<>())
                .initialAmount(30.0)
                .build();

        when(categoryRepository.findById(anyLong())).thenReturn(Optional.ofNullable(category));
        category.setCategoryName(categoryDto.getCategoryName());
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        CategoryDto categoryDtoTest = categoryService.patchCategoryDto(anyLong(), categoryDto);

        assertEquals(categoryDtoTest.getCategoryName(), categoryDto.getCategoryName());
        assertEquals(categoryDtoTest.getInitialAmount(), category.getInitialAmount());
    }

    @Test
    void deleteByIdTest() {

        Category category = Category.builder()
                .id(1L)
                .categoryName("Garderoba")
                .expenseList(new ArrayList<>())
                .initialAmount(30.0)
                .build();
        when(categoryRepository.findById(1L)).thenReturn(Optional.ofNullable(category));

        categoryService.deleteById(category.getId());

        verify(categoryRepository, times(1)).deleteById(anyLong());
    }
}