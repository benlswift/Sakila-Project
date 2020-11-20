package com.sparta.glowupgirls.sakilaproject.controllers;

import com.sparta.glowupgirls.sakilaproject.entities.*;
import com.sparta.glowupgirls.sakilaproject.services.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
public class AccountController {
    private FilmService filmService;
    private InventoryService inventoryService;
    private CustomerService customerService;
    private RentalService rentalService;
    private StaffService staffService;

    @Autowired
    public AccountController(CustomerService customerService, RentalService rentalService, InventoryService inventoryService, FilmService filmService, StaffService staffService) {
        this.customerService = customerService;
        this.rentalService = rentalService;
        this.inventoryService = inventoryService;
        this.filmService = filmService;
        this.staffService = staffService;
    }

    @GetMapping("/account")
    public String currentUser(ModelMap modelMap, Authentication authentication) {
        String name = authentication.getName();
        Customer customer = customerService.getCustomerByEmail(name);
        List<CustomerOrder> customerOrders = new ArrayList<>();
        if (customer == null) {
            Staff staff = staffService.getStaffByUsername(name);
            if (staff!=null) {
                modelMap.addAttribute("user", staff);
                modelMap.addAttribute("rentals", customerOrders);
                return "account";
            }
        }

        if (customer != null) {
            getCustomerOrders(customer, customerOrders, rentalService, inventoryService, filmService);
        }
        modelMap.addAttribute("user", customer);
        modelMap.addAttribute("rentals", customerOrders);
        return "account";
    }

    static void getCustomerOrders(Customer customer, List<CustomerOrder> customerOrders, RentalService rentalService, InventoryService inventoryService, FilmService filmService) {
        List<Rental> rentals = rentalService.findRentalsByCustomerId(customer.getCustomerId());
        for (Rental rental: rentals) {
            Integer invID = rental.getInventoryId();
            Inventory inventory = inventoryService.findInventoryByInventoryID(invID);
            Film film = filmService.getFilmByID(inventory.getFilmId());
            CustomerOrder customerOrder = new CustomerOrder(film, rental);
            customerOrders.add(customerOrder);
        }
    }
}
