package com.kbtg.techkamp.week1.shop.services;

import com.kbtg.techkamp.week1.shop.models.entities.Cart;
import com.kbtg.techkamp.week1.shop.models.entities.CartItem;
import com.kbtg.techkamp.week1.shop.models.entities.Product;
import com.kbtg.techkamp.week1.shop.models.entities.User;
import com.kbtg.techkamp.week1.shop.models.requests.CartItemRequest;
import com.kbtg.techkamp.week1.shop.models.responses.CartResponse;
import com.kbtg.techkamp.week1.shop.repositories.CartItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CartServiceTest {

    @MockBean
    private UserService userService;

    @MockBean
    private CartItemRepository cartItemRepository;

    @MockBean
    private ProductService productService;


    @Test
    void getCartInfo() {

        Product product1 = new Product(1, "p1", 10.00, 1, "");
        Product product2 = new Product(2, "p2", 20.00, 1, "");

        User user = new User(1, "user", "password");
        Cart cart = new Cart();
        CartItem item1 = new CartItem(1, 1);
        CartItem item2 = new CartItem(2, 1);
        cart.addItem(item1);
        cart.addItem(item2);
        user.setCart(cart);

        when(productService.getPrice(product1.getId())).thenReturn(product1.getPrice());
        when(productService.getPrice(product2.getId())).thenReturn(product2.getPrice());
        when(userService.getUserInfo(user.getId())).thenReturn(Optional.of(user));



        CartService cartService = new CartService();
        cartService.setUserService(userService);
        cartService.setProductService(productService);
        cartService.setCartItemRepository(cartItemRepository);

        CartResponse response = cartService.getCartInfo(1);
        assertEquals(30.00, response.getTotalPrice());
        assertEquals(2, response.getCartItems().size());
    }

    @Test
    void addProductToCart() {


        User user = new User(1, "user", "password");
        Cart cart = new Cart();
        CartItem item1 = new CartItem(1, 1);
        cart.addItem(item1);
        user.setCart(cart);

        when(productService.isProductAvailable(anyInt(), anyInt())).thenReturn(true);
        when(userService.getUserInfo(user.getId())).thenReturn(Optional.of(user));
        when(cartItemRepository.findByCartIdAndProductId(anyInt(), anyInt())).thenReturn(Optional.of(item1));


        CartService cartService = new CartService();
        cartService.setUserService(userService);
        cartService.setProductService(productService);
        cartService.setCartItemRepository(cartItemRepository);

        CartItemRequest request = new CartItemRequest(user.getId(),1 ,1);
        cartService.addProductToCart(request);
        assertEquals(2, item1.getQuantity());

    }

    @Test
    void removeProductFromCart() {


        User user = new User(1, "user", "password");
        Cart cart = new Cart();
        CartItem item1 = new CartItem(1, 3);
        cart.addItem(item1);
        user.setCart(cart);

        when(productService.isProductAvailable(anyInt(), anyInt())).thenReturn(true);
        when(userService.getUserInfo(user.getId())).thenReturn(Optional.of(user));
        when(cartItemRepository.findByCartIdAndProductId(anyInt(), anyInt())).thenReturn(Optional.of(item1));


        CartService cartService = new CartService();
        cartService.setUserService(userService);
        cartService.setProductService(productService);
        cartService.setCartItemRepository(cartItemRepository);

        CartItemRequest request = new CartItemRequest(user.getId(), 1 ,1);
        cartService.removeProductFromCart(request);
        assertEquals(2, item1.getQuantity());
    }
}