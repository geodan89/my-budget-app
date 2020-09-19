package com.springexercise.mybudgetapp.web.controller;

import com.springexercise.mybudgetapp.service.CategoryService;
import com.springexercise.mybudgetapp.web.model.CategoryDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
//import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

class CategoryControllerTest {

    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    void getAllCategoriesTest() throws Exception {
        //given
        CategoryDto category = new CategoryDto();
        category.setCategoryName("Haine");
        category.setInitialAmount(200.00);

        when(categoryService.getAllCategories()).thenReturn(Arrays.asList(category, category));

        mockMvc.perform(get("/api/v1/categories")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryListDto", hasSize(2)));
    }

    @Test
    void getCategoryByIdTest() throws Exception {
        //given
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryName("Sport");
        categoryDto.setInitialAmount(300.00);
        categoryDto.setId("1");

        when(categoryService.getCategoryById(anyLong())).thenReturn(categoryDto);

        mockMvc.perform(get("/api/v1/category/" + categoryDto.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryName", equalTo("Sport")));
    }

    @Test
    void createNewCategoryTest() throws Exception {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryName("Calatorit");
        categoryDto.setInitialAmount(2000.00);
        categoryDto.setId("1");

        when(categoryService.saveNewCategory(ArgumentMatchers.any(CategoryDto.class))).thenReturn(categoryDto);

        mockMvc.perform(post("/api/v1/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"initialAmount\":\"2000\",\"categoryName\":\"Calatorit\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().stringValues("Location", "/api/v1/category/1"));
    }

    @Test
    void updateCategoryTest() throws Exception {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryName("Vacante");
        categoryDto.setInitialAmount(2500.0);
        categoryDto.setId("1");

        when(categoryService.updateCategoryDto(anyLong(), ArgumentMatchers.any(CategoryDto.class)))
                .thenReturn(categoryDto);

        mockMvc.perform(put("/api/v1/category/" + categoryDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"initialAmount\":\"2500\",\"categoryName\":\"Vacante\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryName", equalTo("Vacante")))
                .andExpect(jsonPath("$.categoryId", equalTo("1")));
    }

    @Test
    void deleteCategoryTest() throws Exception {

    }
}