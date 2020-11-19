package com.sparta.glowupgirls.sakilaproject.controllers;

import com.sparta.glowupgirls.sakilaproject.entities.*;
import com.sparta.glowupgirls.sakilaproject.services.CustomerService;
import com.sparta.glowupgirls.sakilaproject.services.FilmService;
import com.sparta.glowupgirls.sakilaproject.services.InventoryService;
import com.sparta.glowupgirls.sakilaproject.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.List;


@Controller
public class AccountController {
    private FilmService filmService;
    private InventoryService inventoryService;
    private CustomerService customerService;
    private RentalService rentalService;

    @Autowired
    public AccountController(CustomerService customerService, RentalService rentalService, InventoryService inventoryService, FilmService filmService) {
        this.customerService = customerService;
        this.rentalService = rentalService;
        this.inventoryService = inventoryService;
        this.filmService = filmService;
    }

    @GetMapping("/account")
    public String currentUser(ModelMap modelMap, Authentication authentication) {
        String name = authentication.getName();
        Customer customer = customerService.getCustomerByEmail(name);
        List<CustomerOrder> customerOrders = new ArrayList<>();
        if (customer != null) {
            List<Rental> rentals = rentalService.findRentalsByCustomerId(customer.getCustomerId());
            for (Rental rental: rentals) {
                Integer invID = rental.getInventoryId();
                Inventory inventory = inventoryService.findInventoryByInventoryID(invID);
                Film film = filmService.getFilmByID(inventory.getFilmId());
                CustomerOrder customerOrder = new CustomerOrder(film, rental);
                customerOrders.add(customerOrder);
            }
        }
        modelMap.addAttribute("user", customer);
        modelMap.addAttribute("rentals", customerOrders);
        return "account";
    }
}
