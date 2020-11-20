package com.sparta.glowupgirls.sakilaproject.controllers;

import com.sparta.glowupgirls.sakilaproject.entities.Customer;
import com.sparta.glowupgirls.sakilaproject.entities.Film;
import com.sparta.glowupgirls.sakilaproject.entities.Inventory;
import com.sparta.glowupgirls.sakilaproject.entities.Staff;
import com.sparta.glowupgirls.sakilaproject.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
    private CustomerService customerService;
    private StaffService staffService;


    @Autowired
    public FilmController(StaffService staffService,CustomerService customerService,
                          RentalService rentalService,FilmService filmService,
                          FilmActorService filmActorService,InventoryService inventoryService) {
        this.filmService = filmService;
        this.filmActorService = filmActorService;
        this.inventoryService = inventoryService;
        this.rentalService = rentalService;
        this.customerService = customerService;
        this.staffService = staffService;
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

    @GetMapping("/film/{id}")
    public String getFilmInfo(@PathVariable("id") int id, ModelMap modelMap, Principal principal) {
        Film film = filmService.getFilmByID(id);
        String name = principal.getName();
        Customer customer = customerService.getCustomerByEmail(name);
        modelMap.addAttribute("availability",filmService.isAvailable(id));
        modelMap.addAttribute("user",customer);
        modelMap.addAttribute("info",film);
        return "filminfo";
    }

    @GetMapping("/rent/{filmid}/{customerid}")
    public String rentFilm(ModelMap modelMap,@PathVariable("filmid") int filmid, @PathVariable("customerid") int customerid){
        List<Inventory> inventoryList = inventoryService.getInventory();
        for (Inventory inventory : inventoryList){
            if (inventory.getFilmId().equals(filmid)){
                LocalDateTime returnDate = LocalDateTime.now().plusDays(filmService.getFilmByID(inventory.getFilmId()).getRentalDuration());
//                inventoryService.deleteInventoryItem(filmid);
                rentalService.saveRental(inventory.getInventoryId(),customerid, Timestamp.valueOf(returnDate));
                break;
            }
        }
        modelMap.addAttribute("rent","Rented");
        return "redirect:/account";
    }

    @GetMapping("/addfilm")
    public String register(ModelMap modelMap) {
        modelMap.addAttribute("film", new Film());
        return "addfilm";
    }

    @PostMapping("/addfilm")
    public String register(@ModelAttribute Film film, ModelMap modelMap, Authentication authentication) {

        String staffUsername = authentication.getName();
        Film checkFilm = filmService.getFilmByTitle(film.getTitle());
        if (checkFilm != null) {
            modelMap.addAttribute("error", "film exists");
            return "addfilm";
        }
        filmService.createFilm(film);
        modelMap.addAttribute("passed", "Film Successfully Added");
        return "addfilm";
    }
}
