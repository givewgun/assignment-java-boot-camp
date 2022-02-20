package com.kbtg.techkamp.week1.shop.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTokenServiceTest {

    @Test
    void generateTokenFromUsername() {
        UserTokenService userTokenService = new UserTokenService();
        String token = userTokenService.generateTokenFromUsername("gun",1);
        assertEquals("Bearer gun,1", token);
    }

    @Test
    void decodeTokenToUsername() {
        UserTokenService userTokenService = new UserTokenService();
        String token = "Bearer gun,1";
        String username = userTokenService.decodeTokenToUsername(token);
        assertEquals("gun", username);
    }

    @Test
    void decodeTokenToUserId() {
        UserTokenService userTokenService = new UserTokenService();
        String token = "Bearer gun,1";
        int userId = userTokenService.decodeTokenToUserId(token);
        assertEquals(1, userId);
    }
}