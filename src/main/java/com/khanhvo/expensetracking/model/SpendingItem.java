package com.khanhvo.expensetracking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpendingItem {
    private String categoryName;
    private String itemId;
    private String itemName;
    private double itemPrice;
    private String itemDescription;
}
