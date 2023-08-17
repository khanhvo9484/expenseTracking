package com.khanhvo.expensetracking.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder // Lombok annotation to generate builder pattern for the object
public class AuthenticationResponse {
    private String token;

}
