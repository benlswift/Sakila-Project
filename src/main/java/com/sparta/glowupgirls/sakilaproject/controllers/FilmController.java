package com.sparta.glowupgirls.sakilaproject.controllers;

import com.sparta.glowupgirls.sakilaproject.entities.Film;
import com.sparta.glowupgirls.sakilaproject.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FilmController {
    private FilmService filmService;


    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/films")
    public String getFilms(ModelMap modelMap, @RequestParam(value = "filter", defaultValue = "No Filter") String filter) {
        List<Film> films;
        List<Film> allFilms;
        if (filter.equals("No Filter")){
            films = filmService.getFilms();
        }
        else {
            films = filmService.getFilmByTitle(filter);
        }
        allFilms = filmService.getFilms();
        modelMap.addAttribute("allFilms", allFilms);
        modelMap.addAttribute("films",films);
        return "films";
    }

    @GetMapping("/{id}")
    public String getFilmInfo(@PathVariable int id, ModelMap modelMap) {
        Film film = filmService.getFilmByID(id);
        modelMap.addAttribute("info",film);
        return "filminfo";
    }
}
