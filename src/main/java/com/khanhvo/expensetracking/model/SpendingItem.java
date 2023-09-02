package com.khanhvo.expensetracking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpendingItem {
    @Builder.Default
    private String itemId= UUID.randomUUID().toString();
    private String categoryName;
    private String itemName;
    private double itemPrice;
    private String itemDescription;
}
