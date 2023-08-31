package com.khanhvo.expensetracking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Builder // Lombok annotation to generate builder pattern for the object
@Data // Lombok annotation to generate getters, setters, toString, hash, equals, etc.
@AllArgsConstructor // Lombok annotation to generate constructor with all fields
@NoArgsConstructor // Lombok annotation to generate no-arg constructor
@Document("permission")
public class Permission {
    @Builder.Default
    private String id= UUID.randomUUID().toString();
    @Field("permissionName")
    private String permissionName;
    @Field("permissionDescription")
    private String permissionDescription;
}
