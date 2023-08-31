package com.khanhvo.expensetracking.DTO.requestDTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReplyInvitationRequest {
    private String userEmail;
    private String groupId;
    private Boolean isAccepted;
    private String invitedBy;
}
