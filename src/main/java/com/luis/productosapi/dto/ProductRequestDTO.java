package com.luis.productosapi.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class ProductRequestDTO {

    @NotBlank(message = "El nombre del producto no debe estar vacío")
    private String name;

    @Positive(message = "El precio del producto debe ser mayor que 0")
    private double price;

    @PositiveOrZero(message = "El stock del producto no puede ser menor que 0")
    private int stock;

    @NotNull(message = "El id de la categoría del producto es un campo obligatorio a incluir. ")
    private Long categoryId;

    public ProductRequestDTO() {}

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public int getStock() {
        return this.stock;
    }

    public Long getCategoryId() {
        return this.categoryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }


}
