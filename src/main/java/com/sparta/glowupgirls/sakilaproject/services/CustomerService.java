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
import java.util.List;

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

    public List<Customer> findAll() {
        return (List<Customer>) customerRepositories.findAll();
    }

    public List<Customer> getCustomersByFirstName(String fname) {
        return customerRepositories.getCustomersByFirstName(fname);
    }

    public List<Customer> getCustomersByLastName(String lname) {
        return customerRepositories.getCustomersByLastName(lname);
    }

    public List<Customer> getCustomersByFirstNameAndLastName(String fname, String lname) {
        return customerRepositories.getCustomersByFirstNameAndLastName(fname, lname);
    }

    public Customer getCustomerById(int id) {
        if (customerRepositories.findById(id).isPresent())
            return customerRepositories.findById(id).get();
        return null;
    }
}
