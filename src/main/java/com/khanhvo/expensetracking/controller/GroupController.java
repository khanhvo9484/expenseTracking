package com.khanhvo.expensetracking.controller;

import com.khanhvo.expensetracking.DTO.requestDTO.CreateGroupRequest;
import com.khanhvo.expensetracking.DTO.requestDTO.InviteUserRequest;
import com.khanhvo.expensetracking.DTO.responseobject.CustomDataResponse;
import com.khanhvo.expensetracking.DTO.responseobject.CustomErrorResponse;
import com.khanhvo.expensetracking.model.Group;
import com.khanhvo.expensetracking.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/group")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;
    @PostMapping("/create")
    public ResponseEntity<?> createGroup(@RequestBody CreateGroupRequest group){
        try{
            Group newGroup= groupService.createGroup(group);
            CustomDataResponse customDataResponse= CustomDataResponse.builder()
                    .message("Group created successfully")
                    .data(newGroup)
                    .build();
            return ResponseEntity.status(HttpStatus.CREATED).body(customDataResponse);
        }
        catch (Exception e){
            CustomErrorResponse errorResponse=CustomErrorResponse.builder()
                    .code("400")
                    .message(e.getMessage())
                    .details("/api/group/create")
                    .hint("")
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

    }
    @PostMapping("/invite")
    public ResponseEntity<?> inviteUser(@RequestBody InviteUserRequest invitation){
        try{
            groupService.inviteUser(invitation);
            CustomDataResponse customDataResponse= CustomDataResponse.builder()
                    .message("User invited successfully")
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.CREATED).body(customDataResponse);
        }
        catch (Exception e){
            CustomErrorResponse errorResponse=CustomErrorResponse.builder()
                    .code("400")
                    .message(e.getMessage())
                    .details("/api/group/invite")
                    .hint("")
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

    }
}
