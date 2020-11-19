package com.sparta.glowupgirls.sakilaproject.services;

import com.sparta.glowupgirls.sakilaproject.entities.Customer;
//import com.sparta.glowupgirls.sakilaproject.entities.CustomerDetails;
import com.sparta.glowupgirls.sakilaproject.repositories.CustomerRepositories;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class CustomerService  {

    private CustomerRepositories customerRepositories;

    public CustomerService(CustomerRepositories customerRepositories) {
        this.customerRepositories = customerRepositories;
    }

    public Customer getCustomerByEmail(String email) {
        return customerRepositories.findCustomerByEmail(email);
    }

    @Transactional
    public void createCustomer(Customer customer) {
        customerRepositories.save(customer);
    }
}
