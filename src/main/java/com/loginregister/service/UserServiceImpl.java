package com.loginregister.service;

import com.loginregister.model.User;
import com.loginregister.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserRepository userRepository;

    @Override
    public User register(User user) {
        user.setRegisterDate(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public User login(String userName, String password) {
        List<User> byUsername = userRepository.findAll();
        User user = byUsername.stream()
                .filter(userData -> userData.getUserName().equals(userName))
                .filter(userData -> userData.getPassword().equals(password))
                .findFirst()
                .get();
        return user;
    }

    @Override
    public User loginUserUsingQuery(String userName, String password) {
        return userRepository.findUserByUsernameAndPassword(userName,password);
    }
}
