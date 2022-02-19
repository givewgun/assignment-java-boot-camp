package com.kbtg.techkamp.week1.shop.controllers;

import com.kbtg.techkamp.week1.shop.models.entities.Product;
import com.kbtg.techkamp.week1.shop.models.exceptions.ProductNotFoundException;
import com.kbtg.techkamp.week1.shop.models.exceptions.UserNotFoundException;
import com.kbtg.techkamp.week1.shop.models.requests.CartItemRequest;
import com.kbtg.techkamp.week1.shop.models.responses.ProductsResponse;
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
    public ProductsResponse addProductToCart() {
        List<Product> products = productService.getAllProducts();
        return new ProductsResponse(products);
    }

    @GetMapping(value = "/products/{id}")
    public Product removeProductFromCart(@PathVariable int id) {
        return productService.getProductById(id).orElseThrow(() -> new ProductNotFoundException());
    }

    @GetMapping("/products")
    @ResponseBody
    public ProductsResponse searchProductByKeywords(@RequestParam String keyword) {
        List<Product> products = productService.searchProducts(keyword);
        return new ProductsResponse(products);
    }

}