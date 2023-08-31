package com.khanhvo.expensetracking.DTO.DTO;

import com.khanhvo.expensetracking.model.Group;
import com.khanhvo.expensetracking.model.InvitedUser;
import com.khanhvo.expensetracking.model.UserInGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDTO {
    private String groupId;
    private String groupName;
    private String groupDescription;
    private UserDTO groupOwner;
    private List<UserInGroup> usersInGroup;
    private List<InvitedUser> invitedUsers;

    public static GroupDTO fromGroup(Group group){
        GroupDTO groupDTO=new GroupDTO();
        groupDTO.setGroupId(group.getId());
        groupDTO.setGroupName(group.getGroupName());
        groupDTO.setGroupDescription(group.getGroupDescription());
        groupDTO.setGroupOwner((group.getGroupOwner()));
        groupDTO.setUsersInGroup(group.getUsersInGroup());
        groupDTO.setInvitedUsers(group.getInvitedUsers());
        return groupDTO;
    }
}
