package com.khanhvo.expensetracking.controller;

import com.khanhvo.expensetracking.DTO.requestDTO.CreatExpenseRequest;
import com.khanhvo.expensetracking.DTO.requestDTO.DeleteExpenseRequest;
import com.khanhvo.expensetracking.DTO.responseobject.CustomDataResponse;
import com.khanhvo.expensetracking.DTO.responseobject.CustomErrorResponse;
import com.khanhvo.expensetracking.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/expense")
public class ExpenseController {
    private final ExpenseService expenseService;
    @PostMapping("/add")
    public ResponseEntity<?> addExpense(@RequestBody CreatExpenseRequest createExpenseRequest) {
        try{
            var newExpense= expenseService.addExpense(createExpenseRequest);
            CustomDataResponse customDataResponse= CustomDataResponse.builder()
                    .data(newExpense)
                    .message("Expense added successfully")
                    .build();
            return ResponseEntity.ok(customDataResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteExpense(@RequestBody DeleteExpenseRequest deleteExpenseRequest) {
        try{
            var result= expenseService.deleteExpense(deleteExpenseRequest);
            CustomDataResponse customDataResponse= CustomDataResponse.builder()
                    .data(deleteExpenseRequest)
                    .message("Expense deleted successfully")
                    .build();
            if(result.equals(1)){
                return ResponseEntity.ok(customDataResponse);
            }
            else{
                customDataResponse.setMessage("Error deleting expense");
                return ResponseEntity.badRequest().body(customDataResponse);

            }
        } catch (Exception e) {
            CustomErrorResponse customErrorResponse= CustomErrorResponse.builder()
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(customErrorResponse);
        }
    }
//
//    @PutMapping("/update")
//    public ResponseEntity<?> updateExpense(@RequestBody CreatExpenseRequest createExpenseRequest) {
//        try{
//            expenseService.updateExpense(createExpenseRequest);
//            return ResponseEntity.ok("Expense updated successfully");
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
}
