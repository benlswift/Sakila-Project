package com.sparta.glowupgirls.sakilaproject.services;


import com.sparta.glowupgirls.sakilaproject.entities.Actor;
import com.sparta.glowupgirls.sakilaproject.entities.Film;
import com.sparta.glowupgirls.sakilaproject.entities.FilmActor;
import com.sparta.glowupgirls.sakilaproject.repositories.FilmActorRepositories;
import com.sparta.glowupgirls.sakilaproject.repositories.FilmRepositories;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilmActorService {

    private FilmActorRepositories filmActorRepositories;


    private FilmService filmService;
    private ActorService actorService;

    public FilmActorService(FilmActorRepositories filmActorRepositories, FilmService filmService, ActorService actorService) {
        this.filmActorRepositories = filmActorRepositories;
        this.filmService = filmService;
        this.actorService = actorService;
    }
      public List<FilmActor> getFilmActorsByActorId(Integer id) {
        return filmActorRepositories.getFilmActorsByActorId(id);
    }

    public List<Film> getFilmByActor(){
        List<Film> films = filmService.getFilms();
        List<FilmActor> filmActors = (List<FilmActor>) filmActorRepositories.findAll();
        List<Film> filmsWithActor = new ArrayList();
        for (Film film : films){
            for (FilmActor actor : filmActors){
                if (actor.getFilmId() == film.getFilmId()){
                    filmsWithActor.add(film);
                }
            }
        }
        return filmsWithActor;

    }

//    public List<Actor> getActorsInFilm(int filmId){
//        List<Film> films = filmService.getFilms();
//        List<FilmActor> filmActors = (List<FilmActor>) filmActorRepositories.findAll();
//        List<Actor> actorsInFilm = new ArrayList();
//        for (Film film : films){
//            if(film.getFilmId().equals(filmId)) {
//                for (FilmActor actor : filmActors) {
//                    if (actor.getFilmId() == film.getFilmId()) {
//                        actorsInFilm.add(actorService.getActorById(actor.getActorId()).get());
//                    }
//                }
//            }
//        }
//        return actorsInFilm;
//
//    }
}
