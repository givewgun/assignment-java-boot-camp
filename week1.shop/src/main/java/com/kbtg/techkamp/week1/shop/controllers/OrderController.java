package com.kbtg.techkamp.week1.shop.controllers;

import com.kbtg.techkamp.week1.shop.models.entities.Order;
import com.kbtg.techkamp.week1.shop.models.exceptions.OrderNotFoundException;
import com.kbtg.techkamp.week1.shop.models.requests.CartItemRequest;
import com.kbtg.techkamp.week1.shop.models.requests.OrderRequest;
import com.kbtg.techkamp.week1.shop.models.responses.CartResponse;
import com.kbtg.techkamp.week1.shop.models.responses.SuccessResponse;
import com.kbtg.techkamp.week1.shop.services.CartService;
import com.kbtg.techkamp.week1.shop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping(value = "/orders")
    public Order orderProductsInCart(@RequestBody OrderRequest request) {
        return orderService.processOrder(request);
    }

    @GetMapping(value = "/orders")
    public Order orderProductsInCart(@RequestParam int orderId) {
        return orderService.getOrderById(orderId).orElseThrow(() -> new OrderNotFoundException());
    }

}