package com.kbtg.techkamp.week1.shop.services;

import com.kbtg.techkamp.week1.shop.models.User;
import com.kbtg.techkamp.week1.shop.models.exceptions.LoginFailedException;
import com.kbtg.techkamp.week1.shop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTokenService tokenService;

//    @Autowired BCryptPasswordEncoder bCryptPasswordEncoder;

    public String login(String username, String password) {
        Optional<User> matchedUser = userRepository.findByUsername(username);
        if(matchedUser.isPresent()) {
            User user = matchedUser.get();
            if(user.getPassword().equals(password)) {
                return tokenService.generateTokenFromUsername(username, user.getId());
            }
        }
        throw new LoginFailedException(username);
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getUsernameFromToken(String token) {
        return tokenService.decodeTokenToUsername(token);
    }

    public Optional<User> getUserInfo(String username) {
        return userRepository.findByUsername(username);
    }

    public void register(User user) {
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}