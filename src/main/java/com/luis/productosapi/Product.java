package com.luis.productosapi;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(
            message = "El nombre de un producto no puede estar vacío"
    )
    private String name;

    @NotNull(
            message = "Todo producto debe pertenecer a una categoria. "
    )
    @ManyToOne
    @JoinColumn(name="id_category")
    private Category category;

    @Positive(
            message = "El precio de un producto debe ser mayor que 0. "
    )
    private double price;

    @PositiveOrZero (
            message = "El stock de un producto debe ser cero o mayor que cero"
    )
    private int stock;

    public Product() {}

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Category getCategory() {
        return this.category;
    }

    public double getPrice() {
        return this.price;
    }

    public int getStock() {
        return this.stock;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
