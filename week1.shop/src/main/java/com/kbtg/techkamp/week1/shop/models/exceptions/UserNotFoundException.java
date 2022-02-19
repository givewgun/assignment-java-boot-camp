package com.kbtg.techkamp.week1.shop.models.exceptions;


public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User not found");
    }
}