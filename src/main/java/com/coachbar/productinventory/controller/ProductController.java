package com.coachbar.productinventory.controller;

import com.coachbar.productinventory.swagger.ProductAPI;
import com.coachbar.productinventory.model.Product;
import com.coachbar.productinventory.model.ProductResponse;
import com.coachbar.productinventory.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController<T> implements ProductAPI<T> {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ProductResponse<T> getAllProducts() {
        List<Product> products=productService.getAllProducts();
        return new ProductResponse<T>(HttpStatus.OK,"All Products available in inventory",products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse<T>> getProductById(@PathVariable String id) {
        Optional<Product> productOptional = productService.getProductById(id);
        if (productOptional.isPresent()) {
            return new ResponseEntity<>(new ProductResponse<>(HttpStatus.OK, "Product found by id " + id, productOptional.get()),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ProductResponse<>(HttpStatus.NOT_FOUND, "Product with id " + id + " not found", null),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ProductResponse<T> >addProduct(@Valid @RequestBody Product product, BindingResult result) {
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            String errorMessage = (fieldError != null) ? fieldError.getDefaultMessage() : "Validation Error";
            return new ResponseEntity<>(new ProductResponse<T>(HttpStatus.BAD_REQUEST, errorMessage, product),HttpStatus.BAD_REQUEST);
        }
        Product addProduct = productService.addProduct(product);
        return  new ResponseEntity<>(new ProductResponse<T>(HttpStatus.CREATED, "Product added to database.", addProduct),HttpStatus.CREATED);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductResponse<T>> updateProductById(@Valid @RequestBody Product product, @PathVariable String id, BindingResult result) {
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            String errorMessage = (fieldError != null) ? fieldError.getDefaultMessage() : "Validation Error";
            return new ResponseEntity<>(new ProductResponse<>(HttpStatus.BAD_REQUEST, errorMessage, product), HttpStatus.BAD_REQUEST);
        }

        Optional<Product> productOptional = productService.getProductById(id);
        if (productOptional.isPresent()) {
            product = productService.updateProductById(product, id);
            return new ResponseEntity<>(new ProductResponse<>(HttpStatus.OK, "Product Id " + id + " is Updated", product), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ProductResponse<>(HttpStatus.NOT_FOUND, "Product with id " + id + " not found", null), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponse<T>> deleteProductById(@PathVariable String id) {
        Optional<Product> productOptional = productService.getProductById(id);
        if (productOptional.isPresent()) {
            String deleted = productService.deleteProductById(id);
            return new ResponseEntity<>(new ProductResponse<T>(HttpStatus.OK, deleted, id),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ProductResponse<T>(HttpStatus.NOT_FOUND, "Product with id " + id + " not found", null),HttpStatus.NOT_FOUND);
        }
    }
}
