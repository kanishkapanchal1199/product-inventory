package com.coachbar.productinventory.service;

import com.coachbar.productinventory.model.Product;
import java.util.List;
import java.util.Optional;
public interface ProductService {

    public List<Product> getAllProducts();
    public Optional<Product> getProductById(String id);
    public Product addProduct(Product product);
    public Product updateProductById(Product product,String id);
    public String deleteProductById(String id);


}
