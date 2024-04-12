package com.coachbar.productinventory.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class ProductResponse<T>{

    private HttpStatus status;
    private String message;
    private Object result;
}
