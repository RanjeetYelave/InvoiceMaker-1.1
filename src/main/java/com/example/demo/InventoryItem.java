package com.example.demo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryItem {

    
	@Id
    private String itemName;

    private String hsnSac;

    private String unit; // e.g., Nos, Kg, Ft

    private Double purchasePrice;

    private Double wholesalePrice;

    private Double retailPrice;

    private Double quantity; // Current stock quantity
}
