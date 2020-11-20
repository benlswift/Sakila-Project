package com.sparta.glowupgirls.sakilaproject.repositories;

import com.sparta.glowupgirls.sakilaproject.entities.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepositories extends CrudRepository<Customer, Integer> {
    Customer findCustomerByEmail(String email);
    List<Customer> getCustomersByFirstName(String fname);

    List<Customer> getCustomersByLastName(String lname);

    List<Customer> getCustomersByFirstNameAndLastName(String fname, String lname);
}
