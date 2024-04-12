package com.coachbar.productinventory.service.impl;

import com.coachbar.productinventory.model.Product;
import com.coachbar.productinventory.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("1", "Test Product", "Description", 10.0, 5));
        when(productRepository.findAll()).thenReturn(productList);
        List<Product> result = productService.getAllProducts();
        assertEquals(productList, result);
    }

    @Test
    void testGetProductById() {
        String id = "1";
        Product product = new Product(id, "Test Product", "Description", 10.0, 5);
        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        Optional<Product> result = productService.getProductById(id);
        assertEquals(Optional.of(product), result);
    }

    @Test
    void testAddProduct() {
        Product product = new Product(null, "Test Product", "Description", 10.0, 5);
        when(productRepository.save(any(Product.class))).thenReturn(product);
        Product result = productService.addProduct(product);
        assertEquals(product, result);
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void testUpdateProductById() {
        String id = "1";
        Product existingProduct = new Product(id, "Test Product", "Description", 10.0, 5);
        Product updatedProduct = new Product(id, "Updated Product", "Updated Description", 20.0, 10);
        when(productRepository.findById(id)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);
        Product result = productService.updateProductById(updatedProduct, id);
        assertEquals(updatedProduct, result);
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void testDeleteProductById() {
        String id = "1";
        doNothing().when(productRepository).deleteById(id);
        String result = productService.deleteProductById(id);
        assertEquals("Product Id" + id + " is Successfully Deleted !!!", result);
        verify(productRepository, times(1)).deleteById(id);
    }
}
