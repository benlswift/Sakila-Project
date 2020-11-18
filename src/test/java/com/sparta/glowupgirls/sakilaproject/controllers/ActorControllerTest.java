package com.sparta.glowupgirls.sakilaproject.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ActorControllerTest {

    @Autowired
    private ActorController actorController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void actorControllerTest() {
        Assertions.assertNotNull(actorController);
    }

    @Test
    public void getActors() throws Exception{
        this.mockMvc.perform(get("/actors")).andDo(print()).andExpect(status().isOk()).andExpect(model().attributeExists("actors", "allActors"));
    }
}