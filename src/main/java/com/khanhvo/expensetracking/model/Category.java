package com.khanhvo.expensetracking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document("category")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Category {
    @Builder.Default
    private String id= UUID.randomUUID().toString();
    private String categoryName;
    private String categoryDescription;
    private enum categoryType{
        USER_CUSTOM, DEFAULT
    }
}
