package com.kbtg.techkamp.week1.shop.repositories;

import com.kbtg.techkamp.week1.shop.models.entities.Cart;
import com.kbtg.techkamp.week1.shop.models.entities.CartItem;
import com.kbtg.techkamp.week1.shop.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, String> {

    @Query(value = "SELECT * FROM cart_item c WHERE c.cart_id = :cartId AND c.product_id = :productId", nativeQuery = true)
    Optional<CartItem> findByCartIdAndProductId(@Param("cartId") int cartId,
                                                @Param("productId") int productId);


    List<CartItem> findAllByCartId(int cartId);

    @Modifying
    @Query(value = "DELETE FROM cart_item c WHERE c.cart_id = :cartId", nativeQuery = true)
    void deleteAllByCartId(int cartId);

}