package com.khanhvo.expensetracking.DTO.requestDTO;

import com.khanhvo.expensetracking.model.Member;
import com.khanhvo.expensetracking.model.SpendingItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateExpenseRequest {
    private String expenseId;
    private LocalDate expenseDate;
    private String expenseDescription;
    private List<SpendingItem> updatedSpendingItems;
    private List<Member> updatedSharedWith;
}
