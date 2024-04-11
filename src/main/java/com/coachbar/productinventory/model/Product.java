package com.coachbar.productinventory.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "Product")
public class Product {
    @Id
    private int id;
    private String name;
    private String description;
    private double price;
    private int quantity;
}
