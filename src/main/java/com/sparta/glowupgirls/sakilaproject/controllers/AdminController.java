package com.sparta.glowupgirls.sakilaproject.controllers;

import com.sparta.glowupgirls.sakilaproject.entities.*;
import com.sparta.glowupgirls.sakilaproject.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    private StaffService staffService;
    private CustomerService customerService;
    private AddressService addressService;
    private RentalService rentalService;
    private InventoryService inventoryService;
    private FilmService filmService;

    @Autowired
    public AdminController(StaffService staffService, CustomerService customerService, AddressService addressService, RentalService rentalService, InventoryService inventoryService, FilmService filmService) {
        this.staffService = staffService;
        this.customerService = customerService;
        this.addressService = addressService;
        this.rentalService = rentalService;
        this.inventoryService = inventoryService;
        this.filmService = filmService;
    }

    @GetMapping("/admin")
    public String getAdmin(Principal principal, ModelMap modelMap, @RequestParam(value = "filter", defaultValue = "No Filter") String filter, @RequestParam(value = "filter2", defaultValue = "No Filter") String filter2) {
        List<Customer> customers = new ArrayList<>();
        if (filter.equals("No Filter") && filter2.equals("No Filter")) {
            customers = customerService.findAll();
        } else if (filter2.equals("No Filter")) {
            customers = customerService.getCustomersByFirstName(filter);
        } else if (filter.equals("No Filter")) {
            customers = customerService.getCustomersByLastName(filter2);
        } else {
            customers = customerService.getCustomersByFirstNameAndLastName(filter, filter2);
        }
        String username = principal.getName();
        if (username != null) {
            Staff staff = staffService.getStaffByUsername(username);
            modelMap.addAttribute("staff", staff);
            modelMap.addAttribute("allCustomers", customerService.findAll());
            modelMap.addAttribute("customers", customers);
        }
        return "admin";
    }

    @GetMapping("/admin/{id}")
    public String getCustomerDetails(ModelMap modelMap, @PathVariable int id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer != null) {
            Address address = addressService.getAddressById(customer.getAddressId());
            City city = addressService.getCityByCityId(address.getCityId());
            Country country = addressService.getCountryByCountryId(city.getCountryId());

            List<CustomerOrder> customerOrders = new ArrayList<>();
            List<Rental> rentals = rentalService.findRentalsByCustomerId(customer.getCustomerId());
            for (Rental rental: rentals) {
                Integer invID = rental.getInventoryId();
                Inventory inventory = inventoryService.findInventoryByInventoryID(invID);
                Film film = filmService.getFilmByID(inventory.getFilmId());
                CustomerOrder customerOrder = new CustomerOrder(film, rental);
                customerOrders.add(customerOrder);
            }
            modelMap.addAttribute("orders", customerOrders);
            modelMap.addAttribute("customer", customer);
            modelMap.addAttribute("address", address);
            modelMap.addAttribute("city", city);
            modelMap.addAttribute("country", country);
        }
        return "customer";
    }
}
