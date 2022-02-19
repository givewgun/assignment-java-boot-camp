package com.kbtg.techkamp.week1.shop.models.exceptions;


public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
        super("Product not found");
    }
}