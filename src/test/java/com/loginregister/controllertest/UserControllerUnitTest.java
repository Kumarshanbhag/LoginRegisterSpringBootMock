package com.loginregister.controllertest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loginregister.controller.UserController;
import com.loginregister.model.User;
import com.loginregister.service.IUserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest (UserController.class)
public class UserControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    IUserService loginRegisterService;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, World")));
    }

    @Test
    public void givenRegisterApi_WhenUserBodySent_ShouldReturnUser() throws Exception {
        User user = new User("Kumar", "Kumar123", "kumar@gmail.com", "Mumbai");
        String requestJson = this.mapToJson(user);
        given(loginRegisterService.register(any(User.class))).willReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/register")
                .accept(MediaType.APPLICATION_JSON).content(requestJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = this.mockMvc.perform(requestBuilder)
                .andReturn();
        System.out.println(mvcResult);
        MockHttpServletResponse response = mvcResult.getResponse();
        String outputInJson = response.getContentAsString();
        Assert.assertEquals(outputInJson, requestJson);
    }

    @Test
    public void givenLoginApi_WhenUserBodySent_ShouldReturnUser() throws Exception {
        User user = new User("Kumar", "Kumar123", "kumar@gmail.com", "Mumbai");
        String requestJson = this.mapToJson(user);
        given(loginRegisterService.login(any(String.class), any(String.class))).willReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login?userName=Kumar&password=Kumar123")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = this.mockMvc.perform(requestBuilder)
                .andReturn();
        System.out.println(mvcResult);
        MockHttpServletResponse response = mvcResult.getResponse();
        String outputInJson = response.getContentAsString();
        Assert.assertEquals(outputInJson, requestJson);
    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}