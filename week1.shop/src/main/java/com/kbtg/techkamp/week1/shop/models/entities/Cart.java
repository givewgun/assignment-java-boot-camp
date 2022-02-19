package com.kbtg.techkamp.week1.shop.models.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @OneToOne(mappedBy = "cart")
    private User user;

    @OneToMany(mappedBy="cart", cascade = CascadeType.ALL, orphanRemoval=true)
    private Set<CartItem> items;


    public int getId() {
        return id;
    }

    public Cart() {
        this.items = new HashSet<>();
    }

    public Set<CartItem> getItems() {
        return items;
    }

    public void setItems(Set<CartItem> items) {
        for(CartItem item: items){
            item.setCart(this);
        }
        this.items = items;
    }

    public void addItem(CartItem item) {
        item.setCart(this);
        this.items.add(item);
    }


}
