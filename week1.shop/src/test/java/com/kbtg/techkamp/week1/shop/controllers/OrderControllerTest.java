package com.kbtg.techkamp.week1.shop.controllers;

import com.kbtg.techkamp.week1.shop.models.entities.CartItem;
import com.kbtg.techkamp.week1.shop.models.entities.Order;
import com.kbtg.techkamp.week1.shop.models.entities.OrderItem;
import com.kbtg.techkamp.week1.shop.models.requests.OrderRequest;
import com.kbtg.techkamp.week1.shop.models.responses.CartResponse;
import com.kbtg.techkamp.week1.shop.services.OrderService;
import com.kbtg.techkamp.week1.shop.services.UserTokenService;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class OrderControllerTest {


    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private OrderService orderService;


    @Test
    void placeOrder() {
        OrderRequest request = new OrderRequest("cvv", "addr", "0000", "name", "10/02/2022", 1, "4444");
        Order expectedResponse = new Order(1, 1, "addr", "0000", 0.00, new HashSet<>());
        when(orderService.processOrder(any(OrderRequest.class))).thenReturn(expectedResponse);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer user,1");

        HttpEntity<OrderRequest> entity = new HttpEntity<>(request, headers);

        Order result = restTemplate.exchange("/orders", HttpMethod.POST, entity, Order.class).getBody();

        assertEquals(1, result.getId());
        assertEquals("addr", result.getAddress());
        assertEquals("0000", result.getPhone());
    }

    @Test
    void getOrderById() {
        Order expectedResponse = new Order(1, 1, "addr", "0000", 0.00, new HashSet<>());
        when(orderService.getOrderById(anyInt())).thenReturn(Optional.of(expectedResponse));

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer user,1");

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        Order result = restTemplate.exchange("/orders?orderId=1", HttpMethod.GET, entity, Order.class).getBody();

        assertEquals(1, result.getId());
        assertEquals("addr", result.getAddress());
        assertEquals("0000", result.getPhone());
    }
}