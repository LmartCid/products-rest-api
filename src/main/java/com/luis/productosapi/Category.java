package com.luis.productosapi;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La categoria de un producto no puede estar vacía. ")
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public Category() {}

    public Long getId() {
        return this.id;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Product> getProducts() {
        return this.products;
    }
}
