package com.kbtg.techkamp.week1.shop.models.responses;

import com.kbtg.techkamp.week1.shop.models.entities.Product;

import java.util.List;

public class ProductsResponse {

    private List<Product> products;

    public ProductsResponse() {
    }

    public ProductsResponse(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
