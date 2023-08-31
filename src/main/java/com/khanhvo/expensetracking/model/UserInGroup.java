package com.khanhvo.expensetracking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder // Lombok annotation to generate builder pattern for the object
@Data // Lombok annotation to generate getters, setters, toString, hash, equals, etc.
@AllArgsConstructor // Lombok annotation to generate constructor with all fields
@NoArgsConstructor // Lombok annotation to generate no-arg constructor
@Document("user")
public class UserInGroup {
    @Builder.Default
    private String id= UUID.randomUUID().toString();
    @Field("userEmail")
    private String userEmail;
    @Field("permissions")
    private List<String> permissions;
    @Field("groupId")
    private String groupId;
    @Field("invitedBy")
    private String invitedBy;
    @Field("dateJoined")
    @Builder.Default
    private LocalDateTime dateJoined= LocalDateTime.now();
}
