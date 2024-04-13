package com.coachbar.productinventory.controller;


import com.coachbar.productinventory.model.Product;
import com.coachbar.productinventory.model.ProductResponse;
import com.coachbar.productinventory.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController<Product> productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("1", "Test Product", "Description", 10.0, 5));
        when(productService.getAllProducts()).thenReturn(productList);
        ProductResponse<Product> expectedResponse = new ProductResponse<>(HttpStatus.OK, "All Products available in inventory", productList);
        ProductResponse<Product> result = productController.getAllProducts();
        assertEquals(expectedResponse.getStatus(), result.getStatus());
        assertEquals(expectedResponse.getMessage(), result.getMessage());
        assertEquals(expectedResponse.getResult(), result.getResult());
    }


    @Test
    void testGetProductById() {
        String id = "1";
        Product product = new Product(id, "Test Product", "Description", 10.0, 5);
        when(productService.getProductById(id)).thenReturn(Optional.of(product));
        ResponseEntity<ProductResponse<Product>> expectedResponse = ResponseEntity.ok(new ProductResponse<>(HttpStatus.OK, "Product found by id " + id, product));
        ResponseEntity<ProductResponse<Product>> result = productController.getProductById(id);
        assertEquals(expectedResponse.getStatusCode(), result.getStatusCode());
        assertEquals(expectedResponse.getBody().getMessage(), result.getBody().getMessage());
        assertEquals(expectedResponse.getBody().getResult(), result.getBody().getResult());
    }

    @Test
    void testGetProductById_WhenProductDoesNotExist() {
        String id = "2";
        when(productService.getProductById(id)).thenReturn(Optional.empty());

        ResponseEntity<ProductResponse<Product>> expectedResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ProductResponse<>(HttpStatus.NOT_FOUND, "Product with id " + id + " not found", null));
        ResponseEntity<ProductResponse<Product>> result = productController.getProductById(id);

        assertEquals(expectedResponse.getStatusCode(), result.getStatusCode());
        assertEquals(expectedResponse.getBody().getMessage(), result.getBody().getMessage());
        assertEquals(expectedResponse.getBody().getResult(), result.getBody().getResult());
    }


    @Test
    void testAddProduct_WithValidProduct() {
        Product product = new Product(null, "Test Product", "Description", 10.0, 5);
        when(productService.addProduct(any(Product.class))).thenReturn(product);
        ResponseEntity<ProductResponse<Product>> expectedResponse = ResponseEntity.status(HttpStatus.CREATED).body(new ProductResponse<>(HttpStatus.CREATED, "Product added to database.", product));
        ResponseEntity<ProductResponse<Product>> result = productController.addProduct(product, mock(BindingResult.class));
        assertEquals(expectedResponse.getStatusCode(), result.getStatusCode());
        assertEquals(expectedResponse.getBody().getMessage(), result.getBody().getMessage());
        assertEquals(expectedResponse.getBody().getResult(), result.getBody().getResult());
    }

    @Test
    void testAddProduct_WithInvalidProduct() {
        Product invalidProduct = new Product();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getFieldError()).thenReturn(new FieldError("product", "name", "Name cannot be empty"));

        ResponseEntity<ProductResponse<Product>> expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ProductResponse<>(HttpStatus.BAD_REQUEST, "Name cannot be empty", invalidProduct));
        ResponseEntity<ProductResponse<Product>> result = productController.addProduct(invalidProduct, bindingResult);

        assertEquals(expectedResponse.getStatusCode(), result.getStatusCode());
        assertEquals(expectedResponse.getBody().getMessage(), result.getBody().getMessage());
        assertEquals(expectedResponse.getBody().getResult(), result.getBody().getResult());
    }


    @Test
    void testUpdateProductById_WithValidProduct() {
        String id = "1";
        Product product = new Product(id, "Test Product", "Description", 10.0, 5);
        when(productService.getProductById(id)).thenReturn(Optional.of(product));
        when(productService.updateProductById(any(Product.class), eq(id))).thenReturn(product);
        ResponseEntity<ProductResponse<Product>> expectedResponse = ResponseEntity.ok(new ProductResponse<>(HttpStatus.OK, "Product Id " + id + " is Updated", product));
        ResponseEntity<ProductResponse<Product>> result = productController.updateProductById(product, id, mock(BindingResult.class));
        assertEquals(expectedResponse.getStatusCode(), result.getStatusCode());
        assertEquals(expectedResponse.getBody().getMessage(), result.getBody().getMessage());
        assertEquals(expectedResponse.getBody().getResult().toString(), result.getBody().getResult().toString());
    }

    @Test
    void testUpdateProductById_WhenProductDoesNotExist() {
        String id = "2";
        Product product = new Product(id, "Test Product", "Description", 10.0, 5);
        when(productService.getProductById(id)).thenReturn(Optional.empty());

        ResponseEntity<ProductResponse<Product>> expectedResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ProductResponse<>(HttpStatus.NOT_FOUND, "Product with id " + id + " not found", null));
        ResponseEntity<ProductResponse<Product>> result = productController.updateProductById(product, id, mock(BindingResult.class));

        assertEquals(expectedResponse.getStatusCode(), result.getStatusCode());
        assertEquals(expectedResponse.getBody().getMessage(), result.getBody().getMessage());
    }

    @Test
    void testUpdateProductById_WithInvalidProduct() {
        String id = "1";
        Product invalidProduct = new Product();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getFieldError()).thenReturn(new FieldError("product", "name", "Name cannot be empty"));

        ResponseEntity<ProductResponse<Product>> expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ProductResponse<>(HttpStatus.BAD_REQUEST, "Name cannot be empty", invalidProduct));
        ResponseEntity<ProductResponse<Product>> result = productController.updateProductById(invalidProduct, id, bindingResult);

        assertEquals(expectedResponse.getStatusCode(), result.getStatusCode());
        assertEquals(expectedResponse.getBody().getMessage(), result.getBody().getMessage());
        assertEquals(expectedResponse.getBody().getResult().toString(), result.getBody().getResult().toString());
    }

    @Test
    void testDeleteProductById() {
        String id = "1";
        when(productService.getProductById(id)).thenReturn(Optional.of(new Product(id, "Test Product", "Description", 10.0, 5)));
        when(productService.deleteProductById(id)).thenReturn("Product Id" + id + " is Successfully Deleted !!!");
        ResponseEntity<ProductResponse<Product>> expectedResponse = ResponseEntity.ok(new ProductResponse<>(HttpStatus.OK, "Product Id" + id + " is Successfully Deleted !!!", id));
        ResponseEntity<ProductResponse<Product>> result = productController.deleteProductById(id);
        assertEquals(expectedResponse.getStatusCode(), result.getStatusCode());
        assertEquals(expectedResponse.getBody().getMessage(), result.getBody().getMessage());
        assertEquals(expectedResponse.getBody().getResult(), result.getBody().getResult());
    }

    @Test
    void testDeleteProductById_WhenProductDoesNotExist() {
        String id = "2";
        when(productService.getProductById(id)).thenReturn(Optional.empty());

        ResponseEntity<ProductResponse<Product>> expectedResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ProductResponse<>(HttpStatus.NOT_FOUND, "Product with id " + id + " not found", null));
        ResponseEntity<ProductResponse<Product>> result = productController.deleteProductById(id);

        assertEquals(expectedResponse.getStatusCode(), result.getStatusCode());
        assertEquals(expectedResponse.getBody().getMessage(), result.getBody().getMessage());
        assertEquals(expectedResponse.getBody().getResult(), result.getBody().getResult());
    }

}
