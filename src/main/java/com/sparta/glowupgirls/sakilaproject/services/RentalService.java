package com.sparta.glowupgirls.sakilaproject.services;

import com.sparta.glowupgirls.sakilaproject.entities.Rental;
import com.sparta.glowupgirls.sakilaproject.repositories.RentalRepositories;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalService {

    private RentalRepositories rentalRepositories;

    public RentalService(RentalRepositories rentalRepositories) {
        this.rentalRepositories = rentalRepositories;
    }

    public List<Rental> findRentalsByCustomerId(Integer cid) {
        return rentalRepositories.findRentalsByCustomerId(cid);
    }
}
