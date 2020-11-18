package com.sparta.glowupgirls.sakilaproject.services;

import com.sparta.glowupgirls.sakilaproject.entities.Customer;
import com.sparta.glowupgirls.sakilaproject.repositories.CustomerRepositories;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private CustomerRepositories customerRepositories;

    public CustomerService(CustomerRepositories customerRepositories) {
        this.customerRepositories = customerRepositories;
    }

    public Customer getCustomerByEmail(String email) {
        return customerRepositories.findCustomerByEmail(email);
    }
}
