package com.kbtg.techkamp.week1.shop.services;

import com.kbtg.techkamp.week1.shop.models.entities.User;
import com.kbtg.techkamp.week1.shop.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers.*;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserTokenService tokenService;

    @Test
    void login() {
        String expectedToken = "Bearer username 1";
        User user = new User(1,"user", "password");
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        when(tokenService.generateTokenFromUsername(anyString(), anyInt())).thenReturn(expectedToken);

        UserService userService = new UserService();
        userService.setUserRepository(userRepository);
        userService.setUserTokenService(tokenService);

        String token = userService.login(user.getUsername(), user.getPassword());
        assertEquals(expectedToken, token);
    }

    @Test
    void register() {

        String expectedToken = "Bearer username 1";
        User user = new User(1,"user", "password");
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        when(tokenService.generateTokenFromUsername(anyString(), anyInt())).thenReturn(expectedToken);

        UserService userService = new UserService();
        userService.setUserRepository(userRepository);
        userService.setUserTokenService(tokenService);

        userService.register(user);
        verify(userRepository, times(1)).save(user);

    }
    
}