package com.springexercise.mybudgetapp.web.controller;

import com.springexercise.mybudgetapp.service.CategoryService;
import com.springexercise.mybudgetapp.web.model.CategoryDto;
import com.springexercise.mybudgetapp.web.model.CategoryListDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<CategoryListDto> getAllCategories() {
        List<CategoryDto> list = categoryService.getAllCategories();
        CategoryListDto categoryListDto = new CategoryListDto();
        categoryListDto.setCategoryListDto(list);

        return new ResponseEntity<>(categoryListDto, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("categoryId") String categoryId) {
        Long catId = Long.valueOf(categoryId);

        CategoryDto categoryDto = categoryService.getCategoryById(catId);

        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

    @PostMapping("/category")
    public ResponseEntity<CategoryDto> createNewCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto savedCategoryDto = categoryService.saveNewCategory(categoryDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/category/" + savedCategoryDto.getId());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/category/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable("categoryId") String categoryId,
                                                      @RequestBody CategoryDto categoryDto) {
        Long catId = Long.valueOf(categoryId);

        return new ResponseEntity<>(categoryService.updateCategoryDto(catId, categoryDto), HttpStatus.OK);
    }

    @PatchMapping("/category/{categoryId}")
    public ResponseEntity<CategoryDto> patchCategory(@PathVariable("categoryId") String categoryId,
                                                     @RequestBody CategoryDto categoryDto) {
        Long catId = Long.valueOf(categoryId);

        return new ResponseEntity<>(categoryService.patchCategoryDto(catId, categoryDto), HttpStatus.OK);
    }

    @DeleteMapping("/category/{categoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable String categoryId) {
        Long catId = Long.valueOf(categoryId);

        categoryService.deleteById(catId);
    }
}
