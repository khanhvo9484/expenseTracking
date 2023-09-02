package com.khanhvo.expensetracking.controller;

import com.khanhvo.expensetracking.DTO.requestDTO.AddCategoryRequest;
import com.khanhvo.expensetracking.DTO.responseobject.CustomDataResponse;
import com.khanhvo.expensetracking.DTO.responseobject.CustomErrorResponse;
import com.khanhvo.expensetracking.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@RequestBody AddCategoryRequest addCategoryRequestList) {
        try{
            categoryService.addCategory(addCategoryRequestList);
            CustomDataResponse customDataResponse= CustomDataResponse.builder()
                    .data(addCategoryRequestList)
                    .message("Category added successfully")
                    .build();
            return ResponseEntity.ok(customDataResponse);
        }
        catch (Exception e) {
            CustomErrorResponse customErrorResponse= CustomErrorResponse.builder()
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(customErrorResponse);
        }


    }

}
