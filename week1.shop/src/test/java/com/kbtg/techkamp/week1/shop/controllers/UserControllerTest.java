package com.kbtg.techkamp.week1.shop.controllers;

import com.kbtg.techkamp.week1.shop.models.entities.User;
import com.kbtg.techkamp.week1.shop.models.requests.LoginRequest;
import com.kbtg.techkamp.week1.shop.models.responses.LoginSuccessResponse;
import com.kbtg.techkamp.week1.shop.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {


    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private UserService userService;


    @Test
    void login() {
        LoginSuccessResponse expectedResponse = new LoginSuccessResponse("Bearer user 1");
        when(userService.login(anyString(), anyString())).thenReturn(expectedResponse.getToken());
        LoginRequest request = new LoginRequest("user", "password");

        LoginSuccessResponse result = restTemplate.postForObject("/login", request, LoginSuccessResponse.class);

        assertEquals(expectedResponse.getToken(), result.getToken());
    }

    @Test
    void signUp() {
        String expectedResponse = "Signup successful";
        doNothing().when(userService).register(any(User.class));
        User request = new User();
        String result = restTemplate.postForObject("/signup", request, String.class);
        assertEquals(expectedResponse, result);
    }

    @Test
    void getUserInfo() {
        User expectedResponse = new User();
        expectedResponse.setUsername("user");
        doReturn(Optional.of(expectedResponse)).when(userService).getUserInfo(anyInt());
        User result = restTemplate.getForObject("/users?userId=1", User.class);
        assertEquals(expectedResponse.getUsername(), result.getUsername());
    }

}