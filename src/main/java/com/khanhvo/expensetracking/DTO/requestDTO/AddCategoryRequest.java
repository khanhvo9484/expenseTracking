package com.khanhvo.expensetracking.DTO.requestDTO;

import com.khanhvo.expensetracking.DTO.DTO.CategoryDTO;
import com.khanhvo.expensetracking.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddCategoryRequest {
    private List<CategoryDTO> categoryList;
}
