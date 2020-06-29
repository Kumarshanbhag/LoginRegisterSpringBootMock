package com.loginregister.service;

import com.loginregister.model.User;

public interface IUserService {
    User register(User user);

    User login(String emailId, String password);
}
