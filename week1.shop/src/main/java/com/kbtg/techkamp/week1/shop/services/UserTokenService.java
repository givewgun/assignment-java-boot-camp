package com.kbtg.techkamp.week1.shop.services;

import com.kbtg.techkamp.week1.shop.models.exceptions.LoginFailedException;
import org.springframework.stereotype.Service;


@Service
public class UserTokenService {
    public String generateTokenFromUsername(String username, int userId) {
        return String.format("Bearer %s %d", username, userId);
    }

    public String decodeTokenToUsername(String authorizationHeaderValue ) {
        if (authorizationHeaderValue.startsWith("Bearer ")){
            return authorizationHeaderValue.substring(7);
        } else {
            throw new LoginFailedException("invalid token");
        }
    }
}