package com.luis.productosapi.service;
import com.luis.productosapi.Category;
import com.luis.productosapi.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createNewCategory(Category newCategory) {
        return categoryRepository.save(newCategory);
    }
}
