package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InventoryService {

    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    /**
     * Add a new item to the inventory.
     */
    public InventoryItem addItem(InventoryItem inventoryItem) {
        return inventoryItemRepository.save(inventoryItem);
    }

    /**
     * Update an existing item in the inventory by its ID.
     */
    public InventoryItem updateItem(String id, InventoryItem updatedItem) {
        return inventoryItemRepository.findById(id)
                .map(item -> {
                    item.setItemName(updatedItem.getItemName());
                    item.setHsnSac(updatedItem.getHsnSac());
                    item.setUnit(updatedItem.getUnit());
                    item.setPurchasePrice(updatedItem.getPurchasePrice());
                    item.setWholesalePrice(updatedItem.getWholesalePrice());
                    item.setRetailPrice(updatedItem.getRetailPrice());
                    item.setQuantity(updatedItem.getQuantity());
                    return inventoryItemRepository.save(item);
                })
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }

    /**
     * Get all items from the inventory.
     */
    public List<InventoryItem> getAllItems() {
        return inventoryItemRepository.findAll();
    }

    /**
     * Delete an item from the inventory.
     */
    public void deleteItem(String id) {
        if (!inventoryItemRepository.existsById(id)) {
            throw new RuntimeException("Item not found");
        }
        inventoryItemRepository.deleteById(id);
    }
}
