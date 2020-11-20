package com.sparta.glowupgirls.sakilaproject.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.glowupgirls.sakilaproject.entities.Customer;
import com.sparta.glowupgirls.sakilaproject.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    @Autowired
    CustomerController customerController;
    @Autowired
    CustomerService customerService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username ="MARY.SMITH@sakilacustomer.org" , password = "pwd",roles = "USER")
    void showUpdateForm() throws Exception {
        mockMvc.perform(get("/update-customer")).andDo(print()).andExpect(status().isOk()).andExpect(model().attributeExists("customer"));

    }

    @Test
    @WithMockUser(username ="MARY.SMITH@sakilacustomer.org" , password = "pwd",roles = "USER")
    void updateCustomer() throws Exception {
        Customer customer = customerService.getCustomerByEmail("MARY.SMITH@sakilacustomer.org");
        customer.setFirstName("test");
        mockMvc.perform(post("/update-customer").content(asJsonString(customer))).andDo(print()).andExpect(status().isOk()).andExpect(model().attributeExists("passed"));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}