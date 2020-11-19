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
    public void createCustomer(String email, String fname, String lname) {
        Customer customer = new Customer();
        customer.setActive((byte) 1);
        customer.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
        customer.setEmail(email);
        customer.setFirstName(fname);
        customer.setLastName(lname);
        customer.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
        customerRepositories.save(customer);
    }
}
