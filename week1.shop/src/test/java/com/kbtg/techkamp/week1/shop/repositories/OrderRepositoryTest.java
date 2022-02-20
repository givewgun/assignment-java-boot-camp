package com.kbtg.techkamp.week1.shop.repositories;

import com.kbtg.techkamp.week1.shop.models.entities.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void findById() {

        Order order = new Order(1, "addr", "0000", 0.00, new HashSet<>());
        orderRepository.save(order);

        Optional<Order> result = orderRepository.findById(1);
        assertTrue(result.isPresent());

    }
}