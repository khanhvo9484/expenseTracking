package com.khanhvo.expensetracking.DTO.requestDTO;

import com.khanhvo.expensetracking.model.Member;
import com.khanhvo.expensetracking.model.SpendingItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatExpenseRequest {
    private String groupId;
    private String userId;
    private String date;
    private List<SpendingItem> spendingItems;
    private List<Member> sharedWith;
    private String description;
    private String createFor;
    public LocalDateTime getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDateTime.parse(date, formatter);
    }
}
