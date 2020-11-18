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
}
