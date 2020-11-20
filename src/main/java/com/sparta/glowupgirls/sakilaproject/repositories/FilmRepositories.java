package com.sparta.glowupgirls.sakilaproject.repositories;

import com.sparta.glowupgirls.sakilaproject.entities.Film;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FilmRepositories extends CrudRepository<Film, Integer> {
    @Override
    Optional<Film> findById(Integer integer);

    Film findFilmByTitle(String title);
}
