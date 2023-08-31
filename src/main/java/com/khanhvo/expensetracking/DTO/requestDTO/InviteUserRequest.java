package com.khanhvo.expensetracking.DTO.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InviteUserRequest {
    private String email;
    private String invitedBy;
    private String groupId;
}
