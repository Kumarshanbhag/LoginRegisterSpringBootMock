/*****************************************************************
 * @Purpose: To Perform Login and Register Operation Based On SQL
 * @Author: Kumar Shanbhag
 * @Date: 26/06/2020
 ****************************************************************/

package com.loginregister.controller;

import com.loginregister.model.User;
import com.loginregister.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {
    @Autowired
    IUserService userService;

    /**
     * @Purpose: To Register User
     * @param user
     * @return User Data Stored In Database
     */
    @GetMapping ("/register")
    public User register(@RequestBody @Valid User user) {
        return userService.register(user);
    }

    /**
     * @Purpose: To Login User
     * @param emailId
     * @param password
     * @return User Data from Data If Present  Or Throw Exception
     */
    @PostMapping ("/login")
    public User loginUser(@RequestParam (value = "emailId") String emailId, @RequestParam (value = "password") String password) {
        return userService.login(emailId, password);
    }
}
