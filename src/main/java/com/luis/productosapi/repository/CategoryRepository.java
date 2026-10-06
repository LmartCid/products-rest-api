package com.luis.productosapi.repository;
import com.luis.productosapi.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
