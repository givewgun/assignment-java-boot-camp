package com.kbtg.techkamp.week1.shop.models.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    private int userId;
    private String address;
    private String phone;
    private Double totalPrices;

    @OneToMany(mappedBy="order", cascade = CascadeType.ALL, orphanRemoval=true)
    private Set<OrderItem> items;

    public Order(int userId, String address, String phone, Set<OrderItem> items, Double totalPrices) {
        this.userId = userId;
        this.address = address;
        this.phone = phone;
        this.items = items;
        this.totalPrices = totalPrices;
    }

    public int getId() {
        return id;
    }

    public Order() {
        this.items = new HashSet<>();
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public void setItems(Set<OrderItem> items) {
        for(OrderItem item: items){
            item.setOrder(this);
        }
        this.items = items;
    }

    public void addItem(OrderItem item) {
        item.setOrder(this);
        this.items.add(item);
    }

    public String getAddress() {
        return address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getTotalPrices() {
        return totalPrices;
    }

    public void setTotalPrices(Double totalPrices) {
        this.totalPrices = totalPrices;
    }
}
