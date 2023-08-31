package com.khanhvo.expensetracking.model;

import com.khanhvo.expensetracking.DTO.DTO.UserDTO;
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
@Document("group")
public class Group {
    @Builder.Default
    private String id= UUID.randomUUID().toString();
    @Field("groupName")
    private String groupName;
    @Field("groupDescription")
    private String groupDescription;
    @Field("groupOwner")
    private UserDTO groupOwner;
    @Builder.Default
    @Field("dateCreated")
    private LocalDateTime dateCreated= LocalDateTime.now();
    @Field("invitedUsers")
    private List<InvitedUser> invitedUsers;
    @Field("usersInGroup")
    private List<UserInGroup> usersInGroup;
}
