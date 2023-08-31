package com.khanhvo.expensetracking.service;

import com.khanhvo.expensetracking.DTO.DTO.ExpenseDTO;
import com.khanhvo.expensetracking.DTO.requestDTO.CreatExpenseRequest;
import com.khanhvo.expensetracking.model.Expenses;
import com.khanhvo.expensetracking.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    public ExpenseDTO addExpense(CreatExpenseRequest createExpenseRequest) {
        Expenses newExpense = Expenses.builder()
                .userId(createExpenseRequest.getUserId())
                .groupId(createExpenseRequest.getGroupId())
                .date(createExpenseRequest.getDate())
                .createFor(createExpenseRequest.getCreateFor())
                .description(createExpenseRequest.getDescription())
                .spendingItems(createExpenseRequest.getSpendingItems())
                .sharedWith(createExpenseRequest.getSharedWith())
                .build();
        try{
            var savedExpense = expenseRepository.save(newExpense);
            return ExpenseDTO.from(savedExpense.getId(),
                    savedExpense.getUserId(), savedExpense.getDate().toString(), savedExpense.getSpendingItems(),
                    savedExpense.getSharedWith(), savedExpense.getDescription(), savedExpense.getCreateFor(),
                    savedExpense.isDeleted(), savedExpense.getDeletedDate(), savedExpense.getModifiedDate(),
                    savedExpense.getBeforeModified());
        }
        catch (Exception e) {
            throw new RuntimeException("Error when saving expense");
        }
    }
}