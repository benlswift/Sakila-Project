package com.sparta.glowupgirls.sakilaproject.repositories;

import com.sparta.glowupgirls.sakilaproject.entities.Inventory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepositories extends CrudRepository<Inventory, Integer> {
}
