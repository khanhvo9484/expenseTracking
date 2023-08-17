package com.khanhvo.expensetracking.auth;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder // Lombok annotation to generate builder pattern for the object
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private String fullName;

}
