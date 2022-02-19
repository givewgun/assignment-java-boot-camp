package com.kbtg.techkamp.week1.shop.models.exceptions;


public class LoginFailedException extends RuntimeException {
    public LoginFailedException(String username) {
        super(String.format("login failed for user: %s", username));
    }
}