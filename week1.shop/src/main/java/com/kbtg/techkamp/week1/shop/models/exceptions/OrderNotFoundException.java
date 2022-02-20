package com.kbtg.techkamp.week1.shop.models.exceptions;


public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException() {
        super("Order not found");
    }
}