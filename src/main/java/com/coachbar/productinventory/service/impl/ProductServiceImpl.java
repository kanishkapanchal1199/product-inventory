package com.coachbar.productinventory.service.impl;

import com.coachbar.productinventory.model.Product;
import com.coachbar.productinventory.repository.ProductRepository;
import com.coachbar.productinventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    public List<Product> getAllProducts() {
        return  productRepository.findAll();
    }
    public Optional<Product> getProductById(String  id) {
        return productRepository.findById(id);
    }
    public Product addProduct(Product product) {
        product.setId(UUID.randomUUID().toString().split("-")[0]);
        return productRepository.save(product);
    }


    public Product updateProductById(Product product, String id) {
        Product updatedProduct=productRepository.findById(id).get();
        updatedProduct.setName(product.getName());
        updatedProduct.setDescription(product.getDescription());
        updatedProduct.setPrice(product.getPrice());
        updatedProduct.setQuantity(product.getQuantity());
        product=productRepository.save(updatedProduct);
        return product;
    }

    public String deleteProductById(String id) {
        productRepository.deleteById(id);
        return "Product Id" + id + " is Successfully Deleted !!!";
    }
}
