package com.sparta.glowupgirls.sakilaproject.repositories;

import com.sparta.glowupgirls.sakilaproject.entities.FilmActor;
import com.sparta.glowupgirls.sakilaproject.entities.FilmActorPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmActorRepositories extends CrudRepository<FilmActor, FilmActorPK> {
    @Override
    List<FilmActor> findAll();
}
