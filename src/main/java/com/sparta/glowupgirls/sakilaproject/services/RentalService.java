package com.sparta.glowupgirls.sakilaproject.services;

import com.sparta.glowupgirls.sakilaproject.entities.Rental;
import com.sparta.glowupgirls.sakilaproject.repositories.RentalRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service

public class RentalService {

    private RentalRepositories rentalRepositories;

    public RentalService(RentalRepositories rentalRepositories) {
        this.rentalRepositories = rentalRepositories;
    }


    public void saveRental(int inventoryId, int customerId, Timestamp returnDate){
        LocalDateTime rentalDate = LocalDateTime.now();
        Rental rental = new Rental();
        rental.setInventoryId(inventoryId);
        rental.setCustomerId(customerId);
        rental.setRentalDate(Timestamp.valueOf(rentalDate));
        rental.setLastUpdate(Timestamp.valueOf(rentalDate));
        rental.setReturnDate(returnDate);
        rental.setStaffId(1);
        rentalRepositories.save(rental);
}
    public List<Rental> findRentalsByCustomerId(Integer cid) {
        return rentalRepositories.findRentalsByCustomerId(cid);

    }
}
