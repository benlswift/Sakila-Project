package com.sparta.glowupgirls.sakilaproject.repositories;

import com.sparta.glowupgirls.sakilaproject.entities.Rental;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RentalRepositories extends CrudRepository<Rental,Integer> {
    List<Rental> findRentalsByCustomerId(Integer cid);
}
