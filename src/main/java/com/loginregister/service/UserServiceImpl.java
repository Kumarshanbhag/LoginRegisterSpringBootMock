/*****************************************************************
 * @Purpose: To Perform Login and Register Operation On SQL
 * @Author: Kumar Shanbhag
 * @Date: 26/06/2020
 ****************************************************************/

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

    /**
     * @Purpose: TO Store Data In database If Correct Or Throw exception
     * @param user
     * @return User Data Or Exception
     */
    @Override
    public User register(User user) {
        user.setRegisterDate(LocalDateTime.now());
        try {
            return userRepository.save(user);
        } catch(Exception e) {
            throw new LoginException("Duplicate Entry", LoginException.Type.DUPLICATE_ENTRY);
        }
    }

    /**
     * @Purpose: TO Retrieve Data From database If Correct Or Throw exception
     * @param emailId
     * @param password
     * @return User Data Or Exception
     */
    @Override
    public User login(String emailId, String password) {
        User user = userRepository.findUserByEmailIdAndPassword(emailId, password);
        if (user != null) {
            return user;
        }
        throw new LoginException("User Not Present", LoginException.Type.USER_NOT_PRESENT);
    }
}