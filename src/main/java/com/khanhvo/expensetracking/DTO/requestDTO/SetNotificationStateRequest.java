package com.khanhvo.expensetracking.DTO.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SetNotificationStateRequest {
    private String userEmail;
    private String notificationId;
    private Boolean isRead;
}
