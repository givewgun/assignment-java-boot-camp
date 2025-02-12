package com.kbtg.techkamp.week1.shop.repositories;

import com.kbtg.techkamp.week1.shop.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);
    Optional<User> findById(int id);
}