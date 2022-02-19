package com.kbtg.techkamp.week1.shop.controllers;

import com.kbtg.techkamp.week1.shop.models.entities.Product;
import com.kbtg.techkamp.week1.shop.models.exceptions.ProductNotFoundException;
import com.kbtg.techkamp.week1.shop.models.exceptions.UserNotFoundException;
import com.kbtg.techkamp.week1.shop.models.requests.CartItemRequest;
import com.kbtg.techkamp.week1.shop.models.responses.SuccessResponse;
import com.kbtg.techkamp.week1.shop.services.CartService;
import com.kbtg.techkamp.week1.shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping(value = "/products/all")
    public List<Product> addProductToCart() {
        return productService.getAllProducts();
    }

    @GetMapping(value = "/products")
    public Product removeProductFromCart(@RequestParam int productId) {
        return productService.getProductById(productId).orElseThrow(() -> new ProductNotFoundException());
    }

}