package com.luis.productosapi.controller;
import com.luis.productosapi.dto.ProductRequestDTO;
import com.luis.productosapi.dto.ProductResponseDTO;
import com.luis.productosapi.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Validated
@RequestMapping("/products")
@RestController
public class ProductController {
    private static final String idMessage = "El id de un producto debe ser mayor que 0";
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProductsController() {
        List<ProductResponseDTO> productList = productService.getAllProducts();
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductByIdController(@PathVariable @Positive(message = idMessage) Long id) {
        ProductResponseDTO productToFind = productService.getProductById(id);
        return ResponseEntity.ok(productToFind);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<List<ProductResponseDTO>> getProductsWithSameCategoryController(@PathVariable @Positive Long id) {
        List<ProductResponseDTO> productsSameCategory = productService.getProductsWithSameCategory(id);
        return ResponseEntity.ok(productsSameCategory);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createNewProductController(@RequestBody @Valid ProductRequestDTO newProductDto) {
        ProductResponseDTO newProductToCreate = productService.createNewProduct(newProductDto);
        return ResponseEntity.status(201).body(newProductToCreate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProductController(@RequestBody @Valid ProductRequestDTO productUpdated,
                                                           @PathVariable @Positive(message = idMessage) Long id) {
        ProductResponseDTO productUpdatedController = productService.updateProduct(productUpdated, id);
        return ResponseEntity.ok(productUpdatedController);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductController(@PathVariable @Positive(message = idMessage) Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }
}