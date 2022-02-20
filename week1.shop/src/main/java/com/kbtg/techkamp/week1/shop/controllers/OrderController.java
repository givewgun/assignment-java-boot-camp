package com.kbtg.techkamp.week1.shop.controllers;

import com.kbtg.techkamp.week1.shop.models.entities.Order;
import com.kbtg.techkamp.week1.shop.models.exceptions.OrderNotFoundException;
import com.kbtg.techkamp.week1.shop.models.requests.CartItemRequest;
import com.kbtg.techkamp.week1.shop.models.requests.OrderRequest;
import com.kbtg.techkamp.week1.shop.models.responses.CartResponse;
import com.kbtg.techkamp.week1.shop.models.responses.SuccessResponse;
import com.kbtg.techkamp.week1.shop.services.CartService;
import com.kbtg.techkamp.week1.shop.services.OrderService;
import com.kbtg.techkamp.week1.shop.services.UserTokenService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserTokenService tokenService;


    @PostMapping(value = "/orders")
    public Order orderProductsInCart(@RequestHeader("Authorization") String token,
                                     @RequestBody OrderRequest request) {
        int userId = tokenService.decodeTokenToUserId(token);
        request.setUserid(userId);
        return orderService.processOrder(request);
    }

    @GetMapping(value = "/orders")
    public Order orderProductsInCart(@RequestHeader("Authorization") String token,
                                     @RequestParam int orderId) {
        int userId = tokenService.decodeTokenToUserId(token);
        return orderService.getOrderById(orderId).orElseThrow(() -> new OrderNotFoundException());
    }

}