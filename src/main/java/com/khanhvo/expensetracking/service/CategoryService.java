package com.khanhvo.expensetracking.service;

import com.khanhvo.expensetracking.DTO.DTO.CategoryDTO;
import com.khanhvo.expensetracking.DTO.requestDTO.AddCategoryRequest;
import com.khanhvo.expensetracking.model.Category;
import com.khanhvo.expensetracking.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public void addCategory(AddCategoryRequest addCategoryRequestList){
        try{
            List<CategoryDTO> categoryList = addCategoryRequestList.getCategoryList();

            for (CategoryDTO categoryDTO : categoryList) {
                Category category= categoryDTO.toCategory();
                if(categoryRepository.findByCategoryName(category.getCategoryName()).isEmpty()){
                    categoryRepository.save(category);
                }
            }
        }
        catch (Exception e){
            throw new RuntimeException("Error adding category");
        }
    }
}
