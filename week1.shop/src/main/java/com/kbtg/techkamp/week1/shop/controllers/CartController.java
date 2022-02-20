package com.kbtg.techkamp.week1.shop.controllers;

import com.kbtg.techkamp.week1.shop.models.entities.Cart;
import com.kbtg.techkamp.week1.shop.models.entities.CartItem;
import com.kbtg.techkamp.week1.shop.models.entities.User;
import com.kbtg.techkamp.week1.shop.models.requests.CartItemRequest;
import com.kbtg.techkamp.week1.shop.models.responses.CartResponse;
import com.kbtg.techkamp.week1.shop.models.responses.SuccessResponse;
import com.kbtg.techkamp.week1.shop.repositories.CartItemRepository;
import com.kbtg.techkamp.week1.shop.services.CartService;
import com.kbtg.techkamp.week1.shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;


    @GetMapping(value = "/cart/items")
    public CartResponse getCartInfo(@RequestParam int userId) {
        return cartService.getCartInfo(userId);
    }

    @PostMapping(value = "/cart/items/add")
    public SuccessResponse addProductToCart(@RequestBody CartItemRequest request) {
        cartService.addProductToCart(request);
        return new SuccessResponse("add product to cart successfully");
    }

    @PostMapping(value = "/cart/items/remove")
    public SuccessResponse removeProductFromCart(@RequestBody CartItemRequest request) {
        cartService.removeProductFromCart(request);
        return new SuccessResponse("removed product from cart successfully");
    }

}