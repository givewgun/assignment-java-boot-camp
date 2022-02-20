package com.kbtg.techkamp.week1.shop.models.exceptions;


public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException() {
        super("User already exist");
    }
}