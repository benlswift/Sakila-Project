package com.sparta.glowupgirls.sakilaproject.controllers;

import com.sparta.glowupgirls.sakilaproject.entities.Actor;
import com.sparta.glowupgirls.sakilaproject.entities.Film;
import com.sparta.glowupgirls.sakilaproject.entities.FilmActor;
import com.sparta.glowupgirls.sakilaproject.services.ActorService;
import com.sparta.glowupgirls.sakilaproject.services.FilmActorService;
import com.sparta.glowupgirls.sakilaproject.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ActorController {
    private ActorService actorService;
    private FilmActorService filmActorService;
    private FilmService filmService;

    @Autowired
    public ActorController(ActorService actorService, FilmActorService filmActorService, FilmService filmService) {
        this.actorService = actorService;
        this.filmActorService = filmActorService;
        this.filmService = filmService;
    }

    @GetMapping("/actors")
    public String getActors(ModelMap modelMap, @RequestParam(value = "filter", defaultValue = "No Filter") String filter, @RequestParam(value = "filter2", defaultValue = "No Filter") String filter2) {
        List<Actor> actors, allActors;
        if (filter.equals("No Filter") && filter2.equals("No Filter")) {
            actors = actorService.getAllActors();
        } else if (filter2.equals("No Filter")){
            actors = actorService.getActorsByFirstName(filter);
        } else if (filter.equals("No Filter")) {
            actors = actorService.getActorsByLastName(filter2);
        } else {
            actors = actorService.getActorsByFirstNameAndLastName(filter, filter2);
        }
        allActors = actorService.getAllActors();
        modelMap.addAttribute("actors", actors);
        modelMap.addAttribute("allActors", allActors);
        return "actors";
    }
    @GetMapping("/actors/{id}")
    public String getActorFilms(ModelMap modelMap, @PathVariable int id) {
        Actor actor = actorService.getActorById(id);
        List<FilmActor> filmActors = filmActorService.getFilmActorsByActorId(id);
        List<Film> films = new ArrayList<>();
        for (FilmActor filmActor : filmActors) {
            Film film = filmService.getFilmByID(filmActor.getFilmId());;
            films.add(film);
        }
        modelMap.addAttribute("actor", actor);
        modelMap.addAttribute("films", films);
        return "actor";
    }
}
