package com.kbtg.techkamp.week1.shop.services;

import com.kbtg.techkamp.week1.shop.models.entities.Cart;
import com.kbtg.techkamp.week1.shop.models.entities.CartItem;
import com.kbtg.techkamp.week1.shop.models.entities.User;
import com.kbtg.techkamp.week1.shop.models.exceptions.CartItemNotFoundException;
import com.kbtg.techkamp.week1.shop.models.exceptions.InvalidProductQuantityException;
import com.kbtg.techkamp.week1.shop.models.exceptions.UserNotFoundException;
import com.kbtg.techkamp.week1.shop.models.requests.CartItemRequest;
import com.kbtg.techkamp.week1.shop.models.responses.CartResponse;
import com.kbtg.techkamp.week1.shop.repositories.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class CartService {

    @Autowired
    private UserService userService;

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    ProductService productService;

    public CartResponse getCartInfo(int userId) {
        Optional<User> userRes = userService.getUserInfo(userId);
        if(userRes.isPresent()){
            User user = userRes.get();
            Cart cart = user.getCart();
            Set<CartItem> cartItems = cart.getItems();
            int totalPrice = 0;
            for (CartItem item: cartItems) {
                totalPrice += item.getQuantity() * productService.getPrice(item.getProductId());
            }
            return new CartResponse(cart.getId(), cartItems, totalPrice);
        }
        throw new UserNotFoundException();
    }

    public User addProductToCart(CartItemRequest request) {
        int productId = request.getProductId();
        int quantity = request.getQuantity();
        int userId = request.getUserId();
        if (productService.isProductAvailable(productId, quantity)){
            Optional<User> userRes = userService.getUserInfo(userId);
            if(userRes.isPresent()){
                User user = userRes.get();
                int cartId = user.getCart().getId();
                Optional<CartItem> itemRes = cartItemRepository.findByCartIdAndProductId(cartId, productId);
                if(itemRes.isPresent()){
                    CartItem item = itemRes.get();
                    item.setQuantity(item.getQuantity() + quantity);
                    cartItemRepository.save(item);
                } else {
                    CartItem newItem = new CartItem(productId, quantity);
                    user.getCart().addItem(newItem);
                    userService.saveUser(user);
                }
                productService.reduceProductQuantity(productId, quantity);
                return user;
            }
            throw new UserNotFoundException();
        } else {
            throw new InvalidProductQuantityException();
        }

    }

    public User removeProductFromCart(CartItemRequest request) {
        int productId = request.getProductId();
        int quantity = request.getQuantity();
        int userId = request.getUserId();
        Optional<User> userRes = userService.getUserInfo(userId);
        if(userRes.isPresent()){
            User user = userRes.get();
            int cartId = user.getCart().getId();
            Optional<CartItem> item = cartItemRepository.findByCartIdAndProductId(cartId, productId);
            if(item.isPresent()){
                CartItem i = item.get();
                int cartQuantity = i.getQuantity();
                if(cartQuantity < quantity) {
                    throw new InvalidProductQuantityException();
                }
                if(i.getQuantity() <= request.getQuantity()){
                    user.getCart().getItems().remove(i);
                    userService.saveUser(user);
                } else {
                    i.setQuantity(i.getQuantity() - quantity);
                    cartItemRepository.save(i);
                }
                productService.addProductQuantity(productId, quantity);
                return user;
            }
            throw new CartItemNotFoundException();
        }
        throw new UserNotFoundException();
    }
}
