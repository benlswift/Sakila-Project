package com.sparta.glowupgirls.sakilaproject.services;

import com.sparta.glowupgirls.sakilaproject.entities.Film;
import com.sparta.glowupgirls.sakilaproject.repositories.FilmRepositories;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class FilmService {

    private FilmRepositories filmRepositories;


    public FilmService(FilmRepositories filmRepositories) {
        this.filmRepositories = filmRepositories;
    }

    public List<Film> getFilms() {
        return (List<Film>) filmRepositories.findAll();
    }

    public Film getFilmByID(int id){
        return filmRepositories.findById(id).get();
    }

    public List<Film> getFilmByPrice(BigDecimal price){
        List<Film> films = new ArrayList<>();
        for (Film film : getFilms()) {
            if (film.getRentalRate() == price){
                films.add(film);
            }
        }
        return films;
    }
    public List<Film> getFilmByTitle(String title){
        List<Film> films = new ArrayList<>();
        for (Film film : getFilms()) {
            if (film.getTitle().equals(title)){
                films.add(film);
            }
        }
        return films;
    }


}
