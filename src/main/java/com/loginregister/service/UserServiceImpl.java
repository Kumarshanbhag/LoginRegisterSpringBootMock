package com.loginregister.service;

import com.loginregister.exception.LoginException;
import com.loginregister.model.User;
import com.loginregister.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserRepository userRepository;

    @Override
    public User register(User user) {
        user.setRegisterDate(LocalDateTime.now());
        try {
            return userRepository.save(user);
        } catch(Exception e) {
            throw new LoginException("Duplicate Entry", LoginException.Type.DUPLICATE_ENTRY);
        }
    }

    @Override
    public User login(String emailId, String password) {
        User user = userRepository.findUserByEmailIdAndPassword(emailId, password);
        if (user != null) {
            return user;
        }
        throw new LoginException("User Not Present", LoginException.Type.USER_NOT_PRESENT);
    }
}