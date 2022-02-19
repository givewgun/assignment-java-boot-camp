package com.kbtg.techkamp.week1.shop.models.exceptions;


public class CartItemNotFoundException extends RuntimeException {
    public CartItemNotFoundException() {
        super("Cart item not found");
    }
}