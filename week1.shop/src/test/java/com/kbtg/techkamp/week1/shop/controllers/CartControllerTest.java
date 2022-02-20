package com.kbtg.techkamp.week1.shop.controllers;

import com.kbtg.techkamp.week1.shop.models.entities.CartItem;
import com.kbtg.techkamp.week1.shop.models.entities.Product;
import com.kbtg.techkamp.week1.shop.models.entities.User;
import com.kbtg.techkamp.week1.shop.models.requests.CartItemRequest;
import com.kbtg.techkamp.week1.shop.models.responses.CartResponse;
import com.kbtg.techkamp.week1.shop.models.responses.ProductsResponse;
import com.kbtg.techkamp.week1.shop.models.responses.SuccessResponse;
import com.kbtg.techkamp.week1.shop.services.CartService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class CartControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    CartService cartService;

    @Test
    void getCartInfo() {
        CartItem item = new CartItem(1, 1, 1);
        Set<CartItem> items = new HashSet<>();
        items.add(item);
        CartResponse expectedResponse = new CartResponse(1, items, 10.00);
        when(cartService.getCartInfo(anyInt())).thenReturn(expectedResponse);

        CartResponse result = restTemplate.getForObject("/cart/items?userId=1", CartResponse.class);

        assertEquals(1, result.getId());
        assertEquals(1, result.getCartItems().size());
        assertEquals(10.00, result.getTotalPrice());
    }

    @Test
    void addProductToCart() {
        User user = new User();
        when(cartService.addProductToCart(any(CartItemRequest.class))).thenReturn(user);

        CartItemRequest request = new CartItemRequest();
        SuccessResponse result = restTemplate.postForObject("/cart/items/add", request, SuccessResponse.class);

        assertEquals("add product to cart successfully", result.getMessage());
    }

    @Test
    void removeProductFromCart() {
        User user = new User();
        when(cartService.removeProductFromCart(any(CartItemRequest.class))).thenReturn(user);

        CartItemRequest request = new CartItemRequest();
        SuccessResponse result = restTemplate.postForObject("/cart/items/remove", request, SuccessResponse.class);

        assertEquals("removed product from cart successfully", result.getMessage());
    }
}