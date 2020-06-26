package com.loginregister.servicetest;

import com.loginregister.model.User;
import com.loginregister.repository.IUserRepository;
import com.loginregister.service.IUserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith (SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    IUserService userService;

    @MockBean
    IUserRepository userRepository;

    @Test
    public void givenUserObject_WhenRegister_ShouldReturnUser() {
        User user = new User("Kumar", "Kumar123", "kumar@gmail.com", "Mumbai");
        when(userRepository.save(user)).thenReturn(user);
        User registeredUser = userService.register(user);
        Assert.assertEquals(registeredUser,user);
    }

    @Test
    public void givenUserNameAndPassword_WhenLogin_ShouldReturnUser () {
        User user = new User("Kumar", "Kumar123", "kumar@gmail.com", "Mumbai");
        List<User> userList = new ArrayList<>();
        userList.add(user);
        when(userRepository.findAll()).thenReturn(userList);
        User loginUser = userService.login("Kumar","Kumar123");
        Assert.assertEquals(loginUser,user);
    }

    @Test
    public void givenUserNameAndPassword_WhenLoginUsingQuery_ShouldReturnUser () {
        User user = new User("Kumar", "Kumar123", "kumar@gmail.com", "Mumbai");
        when(userRepository.findUserByUsernameAndPassword(any(String.class),any(String.class))).thenReturn(user);
        User loginUser = userService.loginUserUsingQuery("Kumar","Kumar123");
        Assert.assertEquals(loginUser,user);
    }
}
