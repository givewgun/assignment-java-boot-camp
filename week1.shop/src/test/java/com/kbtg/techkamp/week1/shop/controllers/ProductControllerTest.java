package com.kbtg.techkamp.week1.shop.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kbtg.techkamp.week1.shop.models.entities.Product;
import com.kbtg.techkamp.week1.shop.models.requests.LoginRequest;
import com.kbtg.techkamp.week1.shop.models.responses.LoginSuccessResponse;
import com.kbtg.techkamp.week1.shop.models.responses.ProductsResponse;
import com.kbtg.techkamp.week1.shop.services.ProductService;
import com.kbtg.techkamp.week1.shop.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class ProductControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private ProductService productService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getAllProducts() throws JsonProcessingException {
        Product product = new Product(1, "name", 100.00, 1, "img.jpg");
        List<Product> products = Arrays.asList(product);
        when(productService.getAllProducts()).thenReturn(products);

        ProductsResponse result = restTemplate.getForObject("/products/all", ProductsResponse.class);

        assertTrue(!result.getProducts().isEmpty());
    }

    @Test
    void getProductById() {
        Product product = new Product(1, "name", 100.00, 1, "img.jpg");
        when(productService.getProductById(anyInt())).thenReturn(Optional.of(product));

        Product result = restTemplate.getForObject("/products/1", Product.class);

        assertEquals(product.getId(), result.getId());
        assertEquals(product.getName(), result.getName());
    }

    @Test
    void searchProductByKeywords() {
        Product product = new Product(1, "name", 100.00, 1, "img.jpg");
        List<Product> products = Arrays.asList(product);
        when(productService.searchProducts(anyString())).thenReturn(products);

        ProductsResponse result = restTemplate.getForObject("/products?keyword=name", ProductsResponse.class);

        assertTrue(!result.getProducts().isEmpty());
        assertEquals(1, result.getProducts().size());
    }
}