package com.khanhvo.expensetracking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalSharedGroup {
    private String groupId;
    private String groupName;
    private String sharedGroupName;
    private List<Member> member;
}
