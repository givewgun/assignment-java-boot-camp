package com.kbtg.techkamp.week1.shop.services;

import com.kbtg.techkamp.week1.shop.models.entities.CartItem;
import com.kbtg.techkamp.week1.shop.models.entities.Order;
import com.kbtg.techkamp.week1.shop.models.requests.OrderRequest;
import com.kbtg.techkamp.week1.shop.models.responses.CartResponse;
import com.kbtg.techkamp.week1.shop.repositories.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class OrderServiceTest {

    @MockBean
    UserService userService;

    @MockBean
    CartService cartService;

    @MockBean
    OrderRepository orderRepository;

    @Test
    void processOrder() {

        OrderRequest request = new OrderRequest("cvv", "addr", "0000", "name", "10/02/2022", 1, "4444");
        Order order = new Order(1, 1, "addr", "0000", 0.00, new HashSet<>());

        CartItem item = new CartItem(1, 1, 1);
        Set<CartItem> items = new HashSet<>();
        items.add(item);
        CartResponse cartInfo = new CartResponse(1, items, 10.00);

        when(cartService.getCartInfo(anyInt())).thenReturn(cartInfo);
        when(orderRepository.save(any(Order.class))).thenReturn(order);
        doNothing().when(cartService).clearCart(anyInt());

        OrderService orderService = Mockito.spy(new OrderService());
        orderService.setCartService(cartService);
        orderService.setUserService(userService);
        orderService.setOrderRepository(orderRepository);
        doNothing().when(orderService).processPayment(any(OrderRequest.class), any(CartResponse.class));

        Order result = orderService.processOrder(request);
        assertEquals(1, result.getId());
        assertEquals(1, result.getUserId());
        assertEquals("addr", result.getAddress());
        verify(orderService, times(1)).processPayment(any(OrderRequest.class), any(CartResponse.class));
        verify(orderRepository, times(1)).save(any(Order.class));
        verify(cartService, times(1)).clearCart(anyInt());


    }

}