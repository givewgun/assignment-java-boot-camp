package com.kbtg.techkamp.week1.shop.controllers;

import com.kbtg.techkamp.week1.shop.models.exceptions.*;
import com.kbtg.techkamp.week1.shop.models.responses.FailedResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public FailedResponse UserNotFoundException(LoginFailedException e) {
        return new FailedResponse(e.getMessage());
    }


    @ExceptionHandler(LoginFailedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public FailedResponse handleLoginFailedException(LoginFailedException e) {
        return new FailedResponse(e.getMessage());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public FailedResponse handleProductNotFound(ProductNotFoundException e) {
        return new FailedResponse(e.getMessage());
    }

    @ExceptionHandler(InvalidProductQuantityException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public FailedResponse handleInvalidProductQuantity(InvalidProductQuantityException e) {
        return new FailedResponse(e.getMessage());
    }

    @ExceptionHandler(CartItemNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public FailedResponse handleInvalidProductQuantity(CartItemNotFoundException e) {
        return new FailedResponse(e.getMessage());
    }

}
