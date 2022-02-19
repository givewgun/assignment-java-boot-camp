package com.kbtg.techkamp.week1.shop.models.responses;

import com.kbtg.techkamp.week1.shop.models.entities.CartItem;

import java.util.Set;

public class CartResponse {
    private int id;
    private Set<CartItem> cartItems;
    private double totalPrice;

    public CartResponse() {
    }

    public CartResponse(int id, Set<CartItem> cartItems, double totalPrice) {
        this.id = id;
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
