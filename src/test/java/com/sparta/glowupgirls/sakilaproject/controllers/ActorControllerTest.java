package com.sparta.glowupgirls.sakilaproject.controllers;

import com.sparta.glowupgirls.sakilaproject.repositories.ActorRepositories;
import com.sparta.glowupgirls.sakilaproject.services.ActorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

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