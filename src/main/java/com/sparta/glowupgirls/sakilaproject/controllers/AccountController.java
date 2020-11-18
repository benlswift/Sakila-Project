package com.sparta.glowupgirls.sakilaproject.controllers;

import com.sparta.glowupgirls.sakilaproject.entities.Customer;
import com.sparta.glowupgirls.sakilaproject.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AccountController {
    private CustomerService customerService;

    @Autowired
    public AccountController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/account")
    public String currentUser(ModelMap modelMap, HttpTrace.Principal principal) {
        String name = principal.getName();
        Customer customer = customerService.getCustomerByEmail(name);
        modelMap.addAttribute("user", customer);
        return "account";
    }
}
