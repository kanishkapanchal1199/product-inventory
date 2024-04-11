package com.coachbar.productinventory.repository;

import com.coachbar.productinventory.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product,Integer> {

}
