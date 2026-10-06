package com.luis.productosapi.service;
import com.luis.productosapi.Category;
import com.luis.productosapi.Product;
import com.luis.productosapi.dto.ProductRequestDTO;
import com.luis.productosapi.dto.ProductResponseDTO;
import com.luis.productosapi.exceptions.CategoryNotFoundException;
import com.luis.productosapi.exceptions.ProductNotFoundException;
import com.luis.productosapi.repository.CategoryRepository;
import com.luis.productosapi.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<ProductResponseDTO> getAllProducts() {
        List<Product> allProducts =  productRepository.findAll();
        List<ProductResponseDTO> allProductsToRespond = new ArrayList<>();

        for(Product product: allProducts) {
            ProductResponseDTO productRespond = convertProductToProductResponse(product);
            allProductsToRespond.add(productRespond);
        }
        return allProductsToRespond;
    }

    public ProductResponseDTO getProductById(Long id) {
        Product productFound =  productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("No se ha encontrado el producto con id " + id));
        return convertProductToProductResponse(productFound);
    }

    public List<ProductResponseDTO> getProductsWithSameCategory(Long categoryId) {
        List<Product> productsWithSameCategory = productRepository.findByCategoryId(categoryId);
        List<ProductResponseDTO> allProductsObtained = new ArrayList<>();

        for(Product product: productsWithSameCategory) {
            ProductResponseDTO productResponse = convertProductToProductResponse(product);
            allProductsObtained.add(productResponse);
        }
        return allProductsObtained;
    }

    public ProductResponseDTO createNewProduct(ProductRequestDTO productRequest) {
        Category categoryToFind = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Categoría no encontrada"));

        Product productToSave = new Product();
        productToSave.setName(productRequest.getName());
        productToSave.setPrice(productRequest.getPrice());
        productToSave.setStock(productRequest.getStock());
        productToSave.setCategory(categoryToFind);
        Product productSaved = productRepository.save(productToSave);
        return convertProductToProductResponse(productSaved);
    }

    @Transactional
    public ProductResponseDTO updateProduct(ProductRequestDTO productUpdated, Long id) {
        Product productFound  = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("No se ha encontrado el producto con id " + id));

        Category categoryFound = categoryRepository.findById(productUpdated.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Categoría no encontrada"));

        productFound.setName(productUpdated.getName());
        productFound.setPrice(productUpdated.getPrice());
        productFound.setStock(productUpdated.getStock());
        productFound.setCategory(categoryFound);
        return convertProductToProductResponse(productFound);
    }

    public void deleteProductById(Long id) {
        productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("No se ha podido encontrar el producto con id " + id));
        productRepository.deleteById(id);
    }


    private ProductResponseDTO convertProductToProductResponse(Product product) {
        ProductResponseDTO productResponse = new ProductResponseDTO();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setPrice(product.getPrice());
        productResponse.setStock(product.getStock());
        productResponse.setCategoryId(product.getCategory().getId());
        productResponse.setCategoryName(product.getCategory().getCategoryName());
        return productResponse;
    }
}
