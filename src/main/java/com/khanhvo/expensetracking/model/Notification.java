package com.khanhvo.expensetracking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    private String from;
    private LocalDateTime createdDate;
    private String content;
    private boolean isRead;
}
