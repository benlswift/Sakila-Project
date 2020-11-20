package com.sparta.glowupgirls.sakilaproject.controllers;


import com.sparta.glowupgirls.sakilaproject.services.FilmService;
import org.junit.jupiter.api.Assertions;
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
class FilmControllerTest {

    @Autowired
    private FilmController filmController;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private FilmService filmService;
    @Test
    void actorControllerTest() {
        Assertions.assertNotNull(filmController);
    }

    @Test
    @WithMockUser(username ="user1" , password = "pwd",roles = "USER")
    public void getFilms() throws Exception{
        this.mockMvc.perform(get("/films")).andDo(print()).andExpect(status().isOk()).andExpect(model().attributeExists("films", "allFilms"));
    }
    @Test
    @WithMockUser(username ="user1" , password = "pwd",roles = "USER")
    public void rentFilmTest() throws Exception{
        this.mockMvc.perform(get("/account")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void availableTest() {
        Assertions.assertEquals(true,filmService.isAvailable(1));
    }
}