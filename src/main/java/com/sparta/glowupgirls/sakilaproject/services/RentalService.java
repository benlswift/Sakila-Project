package com.sparta.glowupgirls.sakilaproject.services;

import com.sparta.glowupgirls.sakilaproject.entities.Rental;
import com.sparta.glowupgirls.sakilaproject.repositories.RentalRepositories;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class RentalService {
    private RentalRepositories rentalRepositories;

    public RentalService(RentalRepositories rentalRepositories) {
        this.rentalRepositories = rentalRepositories;
    }

    public void saveRental(int inventoryId, int customerId, Timestamp returnDate){
        long rentalId = rentalRepositories.count();
        LocalDateTime rentalDate = LocalDateTime.now();
        Rental rental = new Rental();
        rental.setRentalId((int) rentalId);
        rental.setInventoryId(inventoryId);
        rental.setCustomerId(customerId);
        rental.setRentalDate(Timestamp.valueOf(rentalDate));
        rental.setLastUpdate(Timestamp.valueOf(rentalDate));
        rental.setRentalDate(returnDate);
        rentalRepositories.save(rental);
    }
}
