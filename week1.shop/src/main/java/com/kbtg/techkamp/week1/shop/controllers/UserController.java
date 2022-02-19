package com.kbtg.techkamp.week1.shop.controllers;

import com.kbtg.techkamp.week1.shop.models.User;
import com.kbtg.techkamp.week1.shop.models.requests.ApplicationUser;
import com.kbtg.techkamp.week1.shop.models.requests.LoginRequest;
import com.kbtg.techkamp.week1.shop.models.responses.LoginSuccessResponse;
import com.kbtg.techkamp.week1.shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public LoginSuccessResponse login(@RequestBody LoginRequest request) {
        String token = userService.login(request.getUsername(), request.getPassword());
        return new LoginSuccessResponse(token);
    }

    @PostMapping("/signup")
    public String signUp(@RequestBody User user) {
        userService.register(user);
        return "Signup successful";
    }

    @GetMapping("/user/info")
    public User signUp(@RequestParam String username) {
        return userService.getUserInfo(username).orElseThrow(() -> new RuntimeException("shit"));
    }

    @GetMapping("/validate-token")
    public String getCurrentUser(@RequestHeader("Authorization") String token) {
        return "user from token is " + userService.getUsernameFromToken(token);
    }
}