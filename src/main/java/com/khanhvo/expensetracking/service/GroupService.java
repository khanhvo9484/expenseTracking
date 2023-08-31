package com.khanhvo.expensetracking.service;

import com.khanhvo.expensetracking.DTO.DTO.UserDTO;
import com.khanhvo.expensetracking.DTO.requestDTO.CreateGroupRequest;
import com.khanhvo.expensetracking.DTO.requestDTO.InviteUserRequest;
import com.khanhvo.expensetracking.model.Group;
import com.khanhvo.expensetracking.model.InvitedUser;
import com.khanhvo.expensetracking.model.Notification;
import com.khanhvo.expensetracking.model.User;
import com.khanhvo.expensetracking.repository.GroupRepository;
import com.khanhvo.expensetracking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final MongoTemplate mongoTemplate;

    public Group createGroup(CreateGroupRequest group){
        String userId=group.getUserId();
        User user= userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
        UserDTO userDTO= UserDTO.fromUser(user);
        try {
            Group newGroup= Group.builder()
                    .groupName(group.getGroupName())
                    .groupDescription(group.getGroupDescription())
                    .groupOwner(userDTO)
                    .build();
            groupRepository.save(newGroup);
            return newGroup;
        }
        catch (Exception e){
            throw new RuntimeException("Error creating group");
        }
    }
    public void inviteUser(InviteUserRequest invitation ){
        String groupId=invitation.getGroupId();
        String inviterEmail=invitation.getInvitedBy();
        String memberEmail=invitation.getEmail();

        Group group=groupRepository.findById(groupId).orElseThrow(()->new RuntimeException("Group not found"));
        User user=userRepository.findByEmail(inviterEmail).orElseThrow(()->new RuntimeException("User not found"));
        try{
            InvitedUser invitedUser= InvitedUser.builder()
                    .email(memberEmail)
                    .invitedBy(inviterEmail)
                    .build();
            groupRepository.addInvitedUser(groupId,invitedUser);
        }
        catch (Exception e){
            throw new RuntimeException("Error inviting user");
        }

        try {
            Notification notification=Notification.builder()
                    .from(inviterEmail)
                    .content("You have been invited to join group "+group.getGroupName())
                    .build();
            userRepository.addNewNotification(memberEmail,notification);
        }
        catch (Exception e){
            throw new RuntimeException("Error sending notification");
        }


    }


}
