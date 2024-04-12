package com.coachbar.productinventory.swagger;

import com.coachbar.productinventory.model.Product;
import com.coachbar.productinventory.model.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Products", description = "The Products Api")
public interface ProductAPI<T> {
    @Operation(
            summary = "Fetch all Products",
            description = "fetches all Products entities and their data from data source")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation")
    })
    public ProductResponse<T> getAllProducts() ;
    @Operation(
            summary = "Fetch Product based on Product Id",
            description = "fetches Product based on Product Id and their data from data source")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation")
    })
    public ResponseEntity<ProductResponse<T>> getProductById(@PathVariable String id) ;

    @Operation(
            summary = "Adds Product ",
            description = "Add the Product with data into data source")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "successful created")
    })
    public ResponseEntity<ProductResponse<T> >addProduct(@Valid @RequestBody Product product, BindingResult result);

    @Operation(
            summary = "Updates the Product ",
            description = "Updates the Product with product id and its data into data source")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful updated")
    })
    public ResponseEntity<ProductResponse<T>> updateProductById(@Valid @RequestBody Product product, @PathVariable String id, BindingResult result) ;

    @Operation(
            summary = "Deletes the Product ",
            description = "Deletes the Product with product id and its data into data source")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful updated")
    })
    public ResponseEntity<ProductResponse<T>> deleteProductById(@PathVariable String id);
}
