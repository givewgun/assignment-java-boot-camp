package com.kbtg.techkamp.week1.shop.models.exceptions;

public class AuthorizationException extends RuntimeException {
    public AuthorizationException() {
        super("Authorization failed");
    }
}
