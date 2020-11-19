package com.sparta.glowupgirls.sakilaproject.services;

import com.sparta.glowupgirls.sakilaproject.entities.Film;
import com.sparta.glowupgirls.sakilaproject.entities.Inventory;
import com.sparta.glowupgirls.sakilaproject.repositories.ActorRepositories;
import com.sparta.glowupgirls.sakilaproject.repositories.InventoryRepositories;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private InventoryRepositories inventoryRepositories;

    public InventoryService(InventoryRepositories inventoryRepositories) {
        this.inventoryRepositories = inventoryRepositories;
    }

    public List<Inventory> getInventory() {
        return (List<Inventory>) inventoryRepositories.findAll();
    }

    public void deleteInventoryItem(int inventoryId){
        inventoryRepositories.deleteById(inventoryId);
}
    public Inventory findInventoryByInventoryID(Integer invID) {
        if (inventoryRepositories.findById(invID).isPresent())
            return inventoryRepositories.findById(invID).get();
        return null;
    }
}
