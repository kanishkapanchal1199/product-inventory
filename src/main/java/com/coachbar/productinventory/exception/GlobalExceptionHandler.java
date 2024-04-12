package com.coachbar.productinventory.exception;

import com.coachbar.productinventory.model.ProductResponse;
import com.mongodb.MongoException;
import com.mongodb.MongoTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ProductResponse<String> handleBindException(BindException ex) {
        FieldError fieldError = ex.getFieldError();
        String errorMessage = (fieldError != null) ? fieldError.getDefaultMessage() : "Validation Error";
        return new ProductResponse<>(HttpStatus.BAD_REQUEST, errorMessage, null);
    }
    @ExceptionHandler({MongoException.class, MongoTimeoutException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ProductResponse<String> handleMongoException(Exception ex) {
        return new ProductResponse<>(HttpStatus.INTERNAL_SERVER_ERROR,"Error occurred while connecting to MongoDB: " , ex.getMessage());
    }
}
