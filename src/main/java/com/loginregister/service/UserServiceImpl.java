package com.loginregister.service;

import com.loginregister.model.User;
import com.loginregister.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserRepository loginRegisterRepository;

    @Override
    public User register(User user) {
        user.setRegisterDate(LocalDateTime.now());
        return loginRegisterRepository.save(user);
    }
}
