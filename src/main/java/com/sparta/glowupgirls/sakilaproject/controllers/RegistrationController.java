package com.sparta.glowupgirls.sakilaproject.controllers;

import com.sparta.glowupgirls.sakilaproject.entities.Customer;
import com.sparta.glowupgirls.sakilaproject.entities.Staff;
import com.sparta.glowupgirls.sakilaproject.services.CustomerService;
import com.sparta.glowupgirls.sakilaproject.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Controller
public class RegistrationController {

    private CustomerService customerService;
    private StaffService staffService;

    @Autowired
    public RegistrationController(CustomerService customerService, StaffService staffService) {
        this.customerService = customerService;
        this.staffService = staffService;
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
    public String register(@ModelAttribute Customer customer, ModelMap modelMap, Authentication authentication) {

        String staffUsername = authentication.getName();

        Staff staff = staffService.getStaffByUsername(staffUsername);

        Customer checkCustomer = customerService.getCustomerByEmail(customer.getEmail());
        if (checkCustomer != null) {
            modelMap.addAttribute("error", "user exists");
            return "registration";
        }

        customer.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
        customer.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
        customer.setActive((byte) 1);
        customer.setAddressId(staff.getAddressId());
        customer.setStoreId(staff.getStoreId());
        customerService.createCustomer(customer);
        modelMap.addAttribute("passed", "User Successfully Added");
        return "registration";
    }
}
