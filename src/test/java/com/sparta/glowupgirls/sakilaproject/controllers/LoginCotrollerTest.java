package com.sparta.glowupgirls.sakilaproject.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginCotrollerTest {

    @Autowired
    private LoginController loginController;

    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithMockUser(username ="user1" , password = "pwd",roles = "USER")
    public void mytest1 () throws Exception {
        mockMvc.perform(get("/login")).andExpect(status().isOk());

    }




}