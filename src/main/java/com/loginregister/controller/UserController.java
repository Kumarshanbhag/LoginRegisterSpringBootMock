package com.loginregister.controller;

import com.loginregister.model.User;
import com.loginregister.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {
    @Autowired
    IUserService loginRegisterService;

    @GetMapping("/register")
    public User register(@RequestBody @Valid User user) {
        return loginRegisterService.register(user);
    }

    @RequestMapping ("/")
    public @ResponseBody String greeting() {
        return "Hello, World";
    }
}
