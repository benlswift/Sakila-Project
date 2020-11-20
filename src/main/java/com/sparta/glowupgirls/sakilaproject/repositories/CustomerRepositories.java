package com.sparta.glowupgirls.sakilaproject.repositories;

import com.sparta.glowupgirls.sakilaproject.entities.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepositories extends CrudRepository<Customer, Integer> {
    Customer findCustomerByEmail(String email);
    Customer findCustomerByCustomerId(int customerId);
}
