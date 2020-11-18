package com.sparta.glowupgirls.sakilaproject.services;

import com.sparta.glowupgirls.sakilaproject.entities.Actor;
import com.sparta.glowupgirls.sakilaproject.repositories.ActorRepositories;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {

    private ActorRepositories actorRepositories;

    public ActorService(ActorRepositories actorRepositories) {
        this.actorRepositories = actorRepositories;
    }

    public List<Actor> getAllActors() {
        return (List<Actor>) actorRepositories.findAll();
    }

    public List<Actor> getActorsByFirstName(String fname) {
        return (List<Actor>) actorRepositories.findActorsByFirstName(fname);
    }
    public List<Actor> getActorsByLastName(String lname) {
        return (List<Actor>) actorRepositories.findActorsByLastName(lname);
    }

    public List<Actor> getActorsByFirstNameAndLastName(String fname, String lname) {
        return actorRepositories.findActorsByFirstNameAndLastName(fname, lname);
    }

    public Actor getActorById(int id) {
        if (actorRepositories.findById(id).isPresent())
            return actorRepositories.findById(id).get();
        return null;
    }
}
