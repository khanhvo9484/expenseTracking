package com.khanhvo.expensetracking.service;

import com.khanhvo.expensetracking.DTO.requestDTO.ReplyInvitationRequest;
import com.khanhvo.expensetracking.DTO.requestDTO.SetNotificationStateRequest;
import com.khanhvo.expensetracking.model.UserInGroup;
import com.khanhvo.expensetracking.repository.GroupRepository;
import com.khanhvo.expensetracking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    public void replyInvitation(ReplyInvitationRequest replyInvitationRequest) throws Exception {
        var isAccepted = replyInvitationRequest.getIsAccepted();
        var groupId = replyInvitationRequest.getGroupId();
        var userId = replyInvitationRequest.getUserEmail();

        if(groupRepository.findUserInGroupByEmail(groupId, userId).isPresent()) {
            throw new Exception("User is already in group");
        }
        try {
            if (isAccepted) {
                UserInGroup newUserInGroup = UserInGroup.builder()
                        .userEmail(userId)
                        .groupId(groupId)
                        .invitedBy(replyInvitationRequest.getInvitedBy())
                        .permissions(List.of("Hehe"))
                        .build();
                groupRepository.addMemberToGroup(groupId, newUserInGroup);
            }
            groupRepository.removeInvitedUser(groupId, userId);
        }
        catch (Exception e){
            throw new RuntimeException("Error replying invitation");
        }
    }

    public void setNotificationIsRead(SetNotificationStateRequest setNotificationStateRequest){
        var userEmail = setNotificationStateRequest.getUserEmail();
        var notificationId = setNotificationStateRequest.getNotificationId();
        var isRead = setNotificationStateRequest.getIsRead();
        try{
            if(isRead){
                userRepository.setNotificationState(userEmail,notificationId, true);
            }
        }
       catch (Exception e){
           throw new RuntimeException("Error setting notification state");
       }
    }


}
