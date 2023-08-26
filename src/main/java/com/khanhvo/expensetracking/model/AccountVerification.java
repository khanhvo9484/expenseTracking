package com.khanhvo.expensetracking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Builder
@Data // Lombok annotation to generate getters, setters, toString, hash, equals, etc.
@AllArgsConstructor // Lombok annotation to generate constructor with all fields
@NoArgsConstructor
@Document("account_verification")
public class AccountVerification {
    @Field("userEmail")
    private String userEmail;
    @Field("token")
    private String token;

    @Builder.Default
    @Field("createdAt")
    private LocalDateTime createdAt=LocalDateTime.now();

    @Builder.Default
    @Field("expiredAt")
    private LocalDateTime expiredAt=LocalDateTime.now().plusMinutes(15);

    @Field("verified")
    private boolean verified;
}
