package com.khanhvo.expensetracking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("expenses")
public class Expenses {
    @Builder.Default
    private String id= UUID.randomUUID().toString();
    @Field("groupId")
    private String groupId;
    @Field("userId")
    private String userId;
    @Field("date")
    private LocalDateTime date;
    @Field("createdDate")
    private LocalDateTime createdDate= LocalDateTime.now();
    @Field("spendingItems")
    private List<SpendingItem> spendingItems;
    @Field("sharedWith")
    private List<Member> sharedWith;
    @Field("description")
    private String description;
    @Field("createFor")
    private String createFor;
    @Field("isDeleted")
    private boolean isDeleted;
    @Field("deletedDate")
    private LocalDateTime deletedDate;
    @Field("modifiedDate")
    private LocalDateTime modifiedDate;
    @Field("beforeModified")
    private List<SpendingItem> beforeModified;
}
