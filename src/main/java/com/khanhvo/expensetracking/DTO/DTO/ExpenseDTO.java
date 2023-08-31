package com.khanhvo.expensetracking.DTO.DTO;

import com.khanhvo.expensetracking.model.Member;
import com.khanhvo.expensetracking.model.SpendingItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpenseDTO {
    private String expenseId;
    private String userId;
    private String date;
    private List<SpendingItem> spendingItems;
    private List<Member> sharedWith;
    private String description;
    private String createFor;
    private boolean isDeleted;
    private LocalDateTime deletedDate;
    private LocalDateTime modifiedDate;
    private List<SpendingItem> beforeModified;

    public static ExpenseDTO from(String expenseId, String userId, String date, List<SpendingItem> spendingItems, List<Member> sharedWith, String description, String createFor, boolean isDeleted, LocalDateTime deletedDate, LocalDateTime modifiedDate, List<SpendingItem> beforeModified) {
        return ExpenseDTO.builder()
                .expenseId(expenseId)
                .userId(userId)
                .date(date)
                .spendingItems(spendingItems)
                .sharedWith(sharedWith)
                .description(description)
                .createFor(createFor)
                .isDeleted(isDeleted)
                .deletedDate(deletedDate)
                .modifiedDate(modifiedDate)
                .beforeModified(beforeModified)
                .build();
    }
}
