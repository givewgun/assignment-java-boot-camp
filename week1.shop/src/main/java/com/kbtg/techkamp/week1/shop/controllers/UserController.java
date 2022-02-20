package com.kbtg.techkamp.week1.shop.controllers;

import com.kbtg.techkamp.week1.shop.models.entities.User;
import com.kbtg.techkamp.week1.shop.models.exceptions.UserNotFoundException;
import com.kbtg.techkamp.week1.shop.models.requests.LoginRequest;
import com.kbtg.techkamp.week1.shop.models.responses.LoginSuccessResponse;
import com.kbtg.techkamp.week1.shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/users")
    public User getUserInfo(@RequestParam int userId) {
        return userService.getUserInfo(userId).orElseThrow(() -> new UserNotFoundException());
    }

    @GetMapping("/validate-token")
    public String getCurrentUser(@RequestHeader("Authorization") String token) {
        return "user from token is " + userService.getUsernameFromToken(token);
    }
}