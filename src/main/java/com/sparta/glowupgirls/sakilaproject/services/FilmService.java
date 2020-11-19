package com.sparta.glowupgirls.sakilaproject.services;

import com.sparta.glowupgirls.sakilaproject.entities.Film;
import com.sparta.glowupgirls.sakilaproject.entities.Inventory;
import com.sparta.glowupgirls.sakilaproject.repositories.FilmRepositories;
import com.sparta.glowupgirls.sakilaproject.repositories.InventoryRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class FilmService {

    private FilmRepositories filmRepositories;
    private InventoryService inventoryService;

    @Autowired
    public FilmService(FilmRepositories filmRepositories, InventoryService inventoryService) {
        this.filmRepositories = filmRepositories;
        this.inventoryService = inventoryService;
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

    public boolean isAvailable(int filmId){
        List<Inventory> inventoryList = inventoryService.getInventory();
        for (Inventory inventory : inventoryList){
            if (inventory.getFilmId() == filmId){
                return true;
            }
        }
        return false;
    }

    public List<Film> getAvailableFilms(){
        List<Inventory> inventoryList = inventoryService.getInventory();
        List<Film> availableFilms = new ArrayList<>();
        for (Film film : getFilms()){
            for (Inventory inventory : inventoryList){
                if (inventory.getFilmId() == film.getFilmId()){
                    availableFilms.add(film);
                    break;
                }
            }
        }
        return availableFilms;

    }


}
