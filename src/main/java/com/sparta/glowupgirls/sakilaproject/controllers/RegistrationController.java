package com.sparta.glowupgirls.sakilaproject.controllers;

import com.sparta.glowupgirls.sakilaproject.entities.Customer;
import com.sparta.glowupgirls.sakilaproject.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    private CustomerService customerService;

    @Autowired
    public RegistrationController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/registration")
    public String register(ModelMap modelMap) {
//        if (email != null && fname != null && lname != null) {
//            Customer customer = customerService.getCustomerByEmail(email);
//            if (customer != null) {
//                modelMap.addAttribute("error", "user exists");
//                return "registration";
//            }
//            customerService.createCustomer(email, fname, lname);
//            return "index";
//        }
        modelMap.addAttribute("customer", new Customer());
        return "registration";
    }
    @PostMapping("/registration")
    public String register(@ModelAttribute Customer customer, ModelMap modelMap) {
        
        return "registration";
    }
}
