package com.khanhvo.expensetracking.DTO.DTO;

import com.khanhvo.expensetracking.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO {
    private String categoryName;
    private String categoryDescription;

    public Category toCategory(){
        return Category.builder()
                .categoryName(categoryName)
                .categoryDescription(categoryDescription)
                .build();
    }
}
