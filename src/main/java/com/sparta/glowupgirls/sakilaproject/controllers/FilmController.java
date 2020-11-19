package com.sparta.glowupgirls.sakilaproject.controllers;

import com.sparta.glowupgirls.sakilaproject.entities.Film;
import com.sparta.glowupgirls.sakilaproject.entities.Inventory;
import com.sparta.glowupgirls.sakilaproject.services.FilmActorService;
import com.sparta.glowupgirls.sakilaproject.services.FilmService;
import com.sparta.glowupgirls.sakilaproject.services.InventoryService;
import com.sparta.glowupgirls.sakilaproject.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class FilmController {
    private FilmService filmService;
    private FilmActorService filmActorService;
    private InventoryService inventoryService;
    private RentalService rentalService;


    @Autowired
    public FilmController(RentalService rentalService,FilmService filmService, FilmActorService filmActorService,InventoryService inventoryService) {
        this.filmService = filmService;
        this.filmActorService = filmActorService;
        this.inventoryService = inventoryService;
        this.rentalService = rentalService;
    }

    @GetMapping("/films")
    public String getFilms(ModelMap modelMap, @RequestParam(value = "filter", defaultValue = "No Filter") String filter) {
        List<Film> films;
        List<Film> allFilms;
        List<Film> availableFilms = filmService.getAvailableFilms();
        if (filter.equals("No Filter")){
            films = filmService.getFilms();
        }
        else {
            films = filmService.getAvailableFilms();
        }
        allFilms = filmService.getFilms();
        modelMap.addAttribute("availableFilms", availableFilms);
        modelMap.addAttribute("allFilms", allFilms);
        modelMap.addAttribute("films",films);
        return "films";
    }

    @GetMapping("/{id}")
    public String getFilmInfo(@PathVariable int id, ModelMap modelMap) {
        Film film = filmService.getFilmByID(id);
        modelMap.addAttribute("info",film);
        return "filminfo";
    }

    @GetMapping("/rent")
    public String rentFilm(ModelMap modelMap,@RequestParam(value = "filmId")int filmId, @RequestParam(value = "customerId") int customerId){
        List<Inventory> inventoryList = inventoryService.getInventory();
        for (Inventory inventory : inventoryList){
            if (inventory.getFilmId().equals(filmId)){
                LocalDateTime returnDate = LocalDateTime.now().plusDays(filmService.getFilmByID(inventory.getFilmId()).getRentalDuration());
                inventoryService.deleteInventoryItem(filmId);
                rentalService.saveRental(inventory.getInventoryId(),customerId, Timestamp.valueOf(returnDate));
                break;
            }
        }
        return "films";
    }
}
