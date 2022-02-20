package com.kbtg.techkamp.week1.shop.repositories;

import com.kbtg.techkamp.week1.shop.models.entities.Cart;
import com.kbtg.techkamp.week1.shop.models.entities.CartItem;
import com.kbtg.techkamp.week1.shop.models.entities.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CartItemRepositoryTest {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Test
    void findByCartIdAndProductId() {
        Cart cart = new Cart();
        CartItem item = new CartItem(1, 1);
        cart.addItem(item);

        cartRepository.save(cart);

        Optional<CartItem> result = cartItemRepository.findByCartIdAndProductId(1, 1);
        assertTrue(result.isPresent());
    }
}