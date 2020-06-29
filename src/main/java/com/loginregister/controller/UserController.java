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

    @RequestMapping ("/")
    public @ResponseBody
    String greeting() {
        return "Hello, World";
    }

    @GetMapping ("/register")
    public User register(@RequestBody @Valid User user) {
        return userService.register(user);
    }


    @PostMapping ("/login")
    public User loginUser(@RequestParam (value = "emailId") String emailId, @RequestParam (value = "password") String password) {
        return userService.login(emailId, password);
    }

}
