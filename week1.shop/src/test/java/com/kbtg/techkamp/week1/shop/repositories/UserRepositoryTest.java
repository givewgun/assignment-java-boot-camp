package com.kbtg.techkamp.week1.shop.repositories;

import com.kbtg.techkamp.week1.shop.models.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByUsername() {
        User user = new User("user", "password");
        userRepository.save(user);

        Optional<User> result = userRepository.findByUsername("gun");
        assertTrue(result.isPresent());
    }

    @Test
    void findById() {
        User user = new User("user", "password");
        userRepository.save(user);

        Optional<User> result = userRepository.findById(1);
        assertTrue(result.isPresent());
    }
}