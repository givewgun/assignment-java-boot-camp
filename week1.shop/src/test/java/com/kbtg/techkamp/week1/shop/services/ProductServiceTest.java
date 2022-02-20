package com.kbtg.techkamp.week1.shop.services;

import com.kbtg.techkamp.week1.shop.models.entities.Product;
import com.kbtg.techkamp.week1.shop.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static reactor.core.publisher.Mono.when;


@ExtendWith(SpringExtension.class)
class ProductServiceTest {

    @MockBean
    private ProductRepository productRepository;


    @Test
    void getPrice() {
        Product product = new Product(1, "name", 100.00, 1, "img.jpg");
        Mockito.when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));

        ProductService productService = new ProductService();
        productService.setProductRepository(productRepository);
        Double price = productService.getPrice(1);
        assertEquals(100.00, price);
    }

    @Test
    void isProductAvailable() {
        Product product = new Product(1, "name", 100.00, 1, "img.jpg");
        Mockito.when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));

        ProductService productService = new ProductService();
        productService.setProductRepository(productRepository);
        Boolean available = productService.isProductAvailable(1, 10);
        assertEquals(false, available);
    }

    @Test
    void addProductQuantity() {
        Product product = new Product(1, "name", 100.00, 1, "img.jpg");
        Mockito.when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));

        ProductService productService = new ProductService();
        productService.setProductRepository(productRepository);
        productService.addProductQuantity(1, 10);
        assertEquals(11, product.getQuantity());
    }

    @Test
    void reduceProductQuantity() {
        Product product = new Product(1, "name", 100.00, 1, "img.jpg");
        Mockito.when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));

        ProductService productService = new ProductService();
        productService.setProductRepository(productRepository);
        productService.reduceProductQuantity(1, 1);
        assertEquals(0, product.getQuantity());
    }

    @Test
    void searchProducts() {
        Product product = new Product(1, "name", 100.00, 1, "img.jpg");
        List<Product> products = Arrays.asList(product);
        Mockito.when(productRepository.findByNameContaining(anyString())).thenReturn(products);

        ProductService productService = new ProductService();
        productService.setProductRepository(productRepository);
        List<Product> matchedProducts = productService.searchProducts("na");
        assertEquals(1, matchedProducts.size());
    }
}