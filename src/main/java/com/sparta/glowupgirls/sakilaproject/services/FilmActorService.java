package com.sparta.glowupgirls.sakilaproject.services;

import com.sparta.glowupgirls.sakilaproject.entities.FilmActor;
import com.sparta.glowupgirls.sakilaproject.repositories.FilmActorRepositories;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmActorService {

    private FilmActorRepositories filmActorRepositories;

    public FilmActorService(FilmActorRepositories filmActorRepositories) {
        this.filmActorRepositories = filmActorRepositories;
    }

    public List<FilmActor> getFilmActorsByActorId(Integer id) {
        return filmActorRepositories.getFilmActorsByActorId(id);
    }
}
