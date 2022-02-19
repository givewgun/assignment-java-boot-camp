package com.kbtg.techkamp.week1.shop.models.responses;

public class FailedResponse {
    private String message;

    public FailedResponse() {
    }

    public FailedResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}