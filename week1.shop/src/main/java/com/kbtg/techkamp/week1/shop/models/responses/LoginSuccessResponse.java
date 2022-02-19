package com.kbtg.techkamp.week1.shop.models.responses;

public class LoginSuccessResponse {
    private String token;

    public LoginSuccessResponse() {
    }

    public LoginSuccessResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}