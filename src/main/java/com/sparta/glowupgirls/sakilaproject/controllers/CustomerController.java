package com.sparta.glowupgirls.sakilaproject.controllers;
import com.sparta.glowupgirls.sakilaproject.entities.Customer;
import com.sparta.glowupgirls.sakilaproject.repositories.CustomerRepositories;
import com.sparta.glowupgirls.sakilaproject.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.exceptions.TemplateInputException;

import java.security.Principal;

@Controller
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/update-customer")
    public String showUpdateForm(Model model, Principal principal) {
        String email = principal.getName();
        Customer customer = customerService.getCustomerByEmail(email);
        if (customer == null) {
            return "redirect:/account";
        }
        model.addAttribute("customer", customer);
        return "update-customer";
    }

    @PostMapping("/update-customer")
    public String updateCustomer(@ModelAttribute Customer customer, Model model, Principal principal) {
        Customer checkCustomer = customerService.getCustomerByEmail(principal.getName());
        if (checkCustomer == null) {
            model.addAttribute("error", "error occurred ");
            return "update-customer";
        }
        checkCustomer.setEmail(customer.getEmail());
        checkCustomer.setFirstName(customer.getFirstName());
        checkCustomer.setLastName(customer.getLastName());
        customerService.updateCustomer(checkCustomer);
        model.addAttribute("passed", "Account information updated successfully");
        return "update-customer";
    }
}
