package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    /**
     * Get all inventory items.
     */
    @GetMapping
    public ResponseEntity<List<InventoryItem>> getAllItems() {
        List<InventoryItem> items = inventoryService.getAllItems();
        return ResponseEntity.ok(items);
    }

    /**
     * Add a new inventory item.
     */
    @PostMapping
    public ResponseEntity<InventoryItem> addItem(@RequestBody InventoryItem inventoryItem) {
        InventoryItem newItem = inventoryService.addItem(inventoryItem);
        return ResponseEntity.ok(newItem);
    }

    /**
     * Update an existing inventory item by its ID.
     */
    @PutMapping("/{id}")
    public ResponseEntity<InventoryItem> updateItem(
            @PathVariable String id, @RequestBody InventoryItem updatedItem) {
        try {
            InventoryItem item = inventoryService.updateItem(id, updatedItem);
            return ResponseEntity.ok(item);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete an inventory item by its ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable String id) {
        try {
            inventoryService.deleteItem(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
