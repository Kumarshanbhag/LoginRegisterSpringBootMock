package com.loginregister.servicetest;

import com.loginregister.exception.LoginException;
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
        Assert.assertEquals(registeredUser, user);
    }

    @Test
    public void givenUserNameAndPassword_WhenLogin_ShouldReturnUser() {
        User user = new User("Kumar", "Kumar123", "kumar@gmail.com", "Mumbai");
        when(userRepository.findUserByEmailIdAndPassword(any(String.class), any(String.class))).thenReturn(user);
        User loginUser = userService.login("Kumar", "Kumar123");
        Assert.assertEquals(loginUser, user);
    }

    @Test
    public void givenUserNameAndPasswordWrong_WhenLogin_ShouldReturnUser() {
        try {
            when(userRepository.findUserByEmailIdAndPassword(any(String.class), any(String.class))).thenReturn(null);
            userService.login("Kumar", "Kumar123");
        } catch(LoginException e) {
            Assert.assertEquals("User Not Present", e.message);
        }
    }

    @Test
    public void givenUserDetailsCorrect_WhenRegister_ShouldReturnUserDetails() {
        User user = new User("Kumar", "Kumar123", "kumar@gmail.com", "Mumbai");
        when(userRepository.save(any(User.class))).thenReturn(user);
        User loginUser = userService.register(user);
        Assert.assertEquals(loginUser, user);
    }

    @Test
    public void givenUserDetailsDuplicate_WhenRegister_ShouldThrowException() {
        User user = new User("Kumar", "Kumar123", "kumar@gmail.com", "Mumbai");
        try {
            when(userRepository.save(any(User.class))).thenThrow(LoginException.class);
            userService.register(user);
        } catch(LoginException e) {
            Assert.assertEquals("Duplicate Entry", e.message);
        }
    }
}
