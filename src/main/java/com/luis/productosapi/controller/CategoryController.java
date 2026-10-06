package com.luis.productosapi.controller;
import com.luis.productosapi.Category;
import com.luis.productosapi.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/categories")
@RestController
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Category> createNewCategoryController(@Valid @RequestBody Category newCategory) {
        Category newCategoryAdded = categoryService.createNewCategory(newCategory);
        return ResponseEntity.status(201).body(newCategoryAdded);
    }
}
