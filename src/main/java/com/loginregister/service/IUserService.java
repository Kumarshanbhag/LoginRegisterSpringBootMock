package com.loginregister.service;

import com.loginregister.model.User;

public interface IUserService {
    User register(User user);

    User login(String userName, String password);

    User loginUserUsingQuery(String userName, String password);
}
