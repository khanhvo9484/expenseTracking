package com.khanhvo.expensetracking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder // Lombok annotation to generate builder pattern for the object
@Data // Lombok annotation to generate getters, setters, toString, hash, equals, etc.
@AllArgsConstructor // Lombok annotation to generate constructor with all fields
@NoArgsConstructor // Lombok annotation to generate no-arg constructor
public class InvitedUser {
    private String email;
    private String invitedBy;
    @Builder.Default
    private LocalDateTime invitedDate = LocalDateTime.now();
}
