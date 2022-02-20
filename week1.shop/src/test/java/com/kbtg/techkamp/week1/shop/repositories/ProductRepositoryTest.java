package com.kbtg.techkamp.week1.shop.repositories;

import com.kbtg.techkamp.week1.shop.models.entities.Product;
import com.kbtg.techkamp.week1.shop.models.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findById() {
        Product product = new Product(1, "name", 100.00, 1, "img.jpg");

        productRepository.save(product);

        Optional<Product> result = productRepository.findById(1);
        assertTrue(result.isPresent());
    }

    @Test
    void findByNameContaining() {
        Product product = new Product(1, "name", 100.00, 1, "img.jpg");

        productRepository.save(product);

        List<Product> result = productRepository.findByNameContaining("na");
        assertTrue(!result.isEmpty());
    }
}