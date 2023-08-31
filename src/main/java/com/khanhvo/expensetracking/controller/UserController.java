package com.khanhvo.expensetracking.controller;

import com.khanhvo.expensetracking.DTO.requestDTO.ReplyInvitationRequest;
import com.khanhvo.expensetracking.DTO.requestDTO.SetNotificationStateRequest;
import com.khanhvo.expensetracking.DTO.responseobject.CustomDataResponse;
import com.khanhvo.expensetracking.DTO.responseobject.CustomErrorResponse;
import com.khanhvo.expensetracking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    @PostMapping("reply-invitation")
    public ResponseEntity<?> replyInvitation(@RequestBody ReplyInvitationRequest replyInvitationRequest) {
        try {
            userService.replyInvitation(replyInvitationRequest);
            CustomDataResponse customDataResponse = CustomDataResponse.builder()
                    .message("Reply invitation successfully")
                    .build();
            return ResponseEntity.ok(customDataResponse);
        } catch (Exception e) {
            CustomErrorResponse customErrorResponse = CustomErrorResponse.builder()
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(customErrorResponse);
        }
    }

    @PostMapping("set-notification-is-read")
    public ResponseEntity<?> setNotificationIsRead(@RequestBody SetNotificationStateRequest setNotificationStateRequest) {
        try {
            userService.setNotificationIsRead(setNotificationStateRequest);
            CustomDataResponse customDataResponse = CustomDataResponse.builder()
                    .message("Set notification is read successfully")
                    .build();
            return ResponseEntity.ok(customDataResponse);
        } catch (Exception e) {
            CustomErrorResponse customErrorResponse = CustomErrorResponse.builder()
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(customErrorResponse);
        }
    }
}
