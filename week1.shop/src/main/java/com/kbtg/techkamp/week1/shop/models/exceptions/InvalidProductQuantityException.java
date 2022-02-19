package com.kbtg.techkamp.week1.shop.models.exceptions;


public class InvalidProductQuantityException extends RuntimeException {
    public InvalidProductQuantityException() {
        super("Invalid product quantity");
    }
}