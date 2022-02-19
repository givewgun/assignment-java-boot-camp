package com.kbtg.techkamp.week1.shop.services;

import com.kbtg.techkamp.week1.shop.models.entities.CartItem;
import com.kbtg.techkamp.week1.shop.models.entities.User;
import com.kbtg.techkamp.week1.shop.models.exceptions.ProductNotFoundException;
import com.kbtg.techkamp.week1.shop.models.exceptions.UserNotFoundException;
import com.kbtg.techkamp.week1.shop.models.requests.CartItemRequest;
import com.kbtg.techkamp.week1.shop.repositories.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private UserService userService;

    @Autowired
    CartItemRepository cartItemRepository;


    public User addProductToCart(CartItemRequest request) {
        Optional<User> userRes = userService.getUserInfo(request.getUserId());
        if(userRes.isPresent()){
            User user = userRes.get();
            int cartId = user.getCart().getId();
            Optional<CartItem> itemRes = cartItemRepository.findByCartIdAndProductId(cartId, request.getProductId());
            if(itemRes.isPresent()){
                CartItem item = itemRes.get();
                item.setQuantity(item.getQuantity() + request.getQuantity());
                cartItemRepository.save(item);
            } else {
                CartItem newItem = new CartItem(request.getProductId(), request.getQuantity());
                user.getCart().addItem(newItem);
                return userService.saveUser(user);
            }
            return user;
        }
        throw new UserNotFoundException();
    }

    public User removeProductFromCart(CartItemRequest request) {
        Optional<User> userRes = userService.getUserInfo(request.getUserId());
        if(userRes.isPresent()){
            User user = userRes.get();
            int cartId = user.getCart().getId();
            Optional<CartItem> item = cartItemRepository.findByCartIdAndProductId(cartId, request.getProductId());
            if(item.isPresent()){
                CartItem i = item.get();
                int quantity = i.getQuantity();
                if(quantity <= request.getQuantity()){
                    user.getCart().getItems().remove(i);
                    userService.saveUser(user);
                } else {
                    i.setQuantity(i.getQuantity() - request.getQuantity());
                    cartItemRepository.save(i);
                }
                return user;
            }
            throw new ProductNotFoundException();
        }
        throw new UserNotFoundException();
    }
}
