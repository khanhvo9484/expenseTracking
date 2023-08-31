package com.khanhvo.expensetracking.DTO.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateGroupRequest {
    private String userId;
    private String groupName;
    private String groupDescription;
}
