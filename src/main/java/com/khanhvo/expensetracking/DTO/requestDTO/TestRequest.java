package com.khanhvo.expensetracking.DTO.requestDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
//@Data
//@AllArgsConstructor
//@Builder // Lombok annotation to generate builder pattern for the object
public class TestRequest {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotBlank
    @NotNull
    private String name;
//    @NotBlank
    @NotNull
    private String email;

    public TestRequest() {
    }
    public TestRequest( String name, String email) {
        this.name = name;
        this.email = email;
    }
}
