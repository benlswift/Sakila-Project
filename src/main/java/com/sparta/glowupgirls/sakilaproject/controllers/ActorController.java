package com.sparta.glowupgirls.sakilaproject.controllers;

import com.sparta.glowupgirls.sakilaproject.entities.Actor;
import com.sparta.glowupgirls.sakilaproject.services.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ActorController {
    private ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
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
}
