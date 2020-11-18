package com.sparta.glowupgirls.sakilaproject.repositories;

import com.sparta.glowupgirls.sakilaproject.entities.Actor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepositories extends CrudRepository<Actor, Integer> {
    List<Actor> findActorsByFirstName(String fname);

    List<Actor> findActorsByLastName(String lname);

    List<Actor> findActorsByFirstNameAndLastName(String fname, String lname);
}
