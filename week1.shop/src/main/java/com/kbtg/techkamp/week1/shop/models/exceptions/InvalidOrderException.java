package com.kbtg.techkamp.week1.shop.models.exceptions;


public class InvalidOrderException extends RuntimeException {
    public InvalidOrderException() {
        super("Invalid order");
    }
}