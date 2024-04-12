package com.coachbar.productinventory.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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
    private String id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    private String description;

    @NotNull(message = "Price cannot be empty")
    @PositiveOrZero(message = "Price must be a positive number or zero")
    private Double price;

    @NotNull(message = "Quantity cannot be empty")
    @PositiveOrZero(message = "Quantity must be a positive number or zero")
    private Integer quantity;
}
