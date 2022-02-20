package com.kbtg.techkamp.week1.shop.services;

import com.kbtg.techkamp.week1.shop.models.entities.*;
import com.kbtg.techkamp.week1.shop.models.exceptions.InvalidOrderException;
import com.kbtg.techkamp.week1.shop.models.requests.OrderRequest;
import com.kbtg.techkamp.week1.shop.models.requests.PaymentRequest;
import com.kbtg.techkamp.week1.shop.models.responses.CartResponse;
import com.kbtg.techkamp.week1.shop.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderService {

    @Autowired
    UserService userService;

    @Autowired
    CartService cartService;

    @Autowired
    OrderRepository orderRepository;


    public Optional<Order> getOrderById(int orderId) {
       return orderRepository.findById(orderId);
    }
    public Order processOrder(OrderRequest orderRequest) {
        CartResponse cartInfo = cartService.getCartInfo(orderRequest.getUserid());
        if(!cartInfo.getCartItems().isEmpty()){
            processPayment(orderRequest, cartInfo);
            return saveOrder(orderRequest, cartInfo);
        }
        throw new InvalidOrderException();
    }


    @Transactional
    private Order saveOrder(OrderRequest orderRequest, CartResponse cartInfo) {
        Set<OrderItem> orderItems = new HashSet<>();
        Order order = new Order(orderRequest.getUserid(), orderRequest.getAddress(), orderRequest.getPhone(), orderItems, cartInfo.getTotalPrice());

        Set<CartItem> cartItems = cartInfo.getCartItems();
        for (CartItem item: cartItems){
            OrderItem orderItem = new OrderItem(item.getProductId(), item.getQuantity());
            order.addItem(orderItem);
        }
        Order savedOrder = orderRepository.save(order);
        cartService.clearCart(cartInfo.getId());
        return savedOrder;
    }

    //TODO call external api to process credit card payment
    /*
        process / call expernal api / separate to payment service etc
     */
    public void processPayment(OrderRequest orderRequest, CartResponse cartInfo){
        PaymentRequest paymentRequest = new PaymentRequest(
                orderRequest.getCvv(),
                orderRequest.getFullName(),
                orderRequest.getExpiry(),
                orderRequest.getCardNumber(),
                cartInfo.getTotalPrice()
        );
        //process / call expernal api / separate to payment service etc
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
}
