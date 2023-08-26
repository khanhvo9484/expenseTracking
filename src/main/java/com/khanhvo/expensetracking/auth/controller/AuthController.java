package com.khanhvo.expensetracking.auth.controller;

import com.khanhvo.expensetracking.DTO.responseobject.CustomDataResponse;
import com.khanhvo.expensetracking.DTO.responseobject.CustomErrorResponse;
import com.khanhvo.expensetracking.auth.DTO.AccountVerifyResponse;
import com.khanhvo.expensetracking.auth.DTO.AuthenticationRequest;
import com.khanhvo.expensetracking.auth.DTO.GenerateVerificationTokenRequest;
import com.khanhvo.expensetracking.auth.service.AuthenticationService;
import com.khanhvo.expensetracking.auth.DTO.RegisterRequest;
import com.khanhvo.expensetracking.auth.service.VerifyRegistrationService;
import com.khanhvo.expensetracking.errorcode.GlobalErrorCodes;
import com.khanhvo.expensetracking.exception.DataConflictException;
import com.khanhvo.expensetracking.model.AccountVerification;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // This is a REST controller
@RequestMapping("/api/auth") // This is the path to the controller
@RequiredArgsConstructor // This is a Lombok annotation that creates a constructor with all required fields
public class AuthController {
    private final AuthenticationService authenticationService;
    private final VerifyRegistrationService verifyRegistrationService;

    @PostMapping("/register") // This is the path to the method
    public ResponseEntity<?> signup(@RequestBody RegisterRequest registerRequest) {
        try {
            var response = authenticationService.register(registerRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            var code = e.getMessage().equals(GlobalErrorCodes.EMAIL_ALREADY_EXISTS) ? GlobalErrorCodes.EMAIL_ALREADY_EXISTS : GlobalErrorCodes.INTERNAL_SERVER_ERROR;
            CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                    .code(code)
                    .message(e.getMessage())
                    .details("/api/auth/register")
                    .build();
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authRequest) {
        try {
            var response = authenticationService.authenticate(authRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                    .code(GlobalErrorCodes.BAD_CREDENTIALS)
                    .message(e.getMessage())
                    .details("/api/auth/login")
                    .build();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("Hello World");
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword() {
//        throw new RuntimeException("Not implemented");
        return ResponseEntity.ok("Hello World");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword() {
        return ResponseEntity.ok("Hello World");
    }

    @PostMapping("/resend-verification")
    public ResponseEntity<String> resendVerification() {
        return ResponseEntity.ok("Hello World");
    }


    @GetMapping("/verify-account/{token}")
    public ResponseEntity<?> verifyEmail(@PathVariable String token) {
        try{
            var verification=verifyRegistrationService.verifyEmail(token);
            var verificationDto= AccountVerifyResponse.mapToDto(verification); // Corrected line: added this line
            CustomDataResponse response = CustomDataResponse.builder()
                    .data(verificationDto)
                    .message("Email verified successfully")
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (DataConflictException e){
            CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                    .code(GlobalErrorCodes.EMAIL_NOT_FOUND)
                    .message(e.getMessage())
                    .details("/api/auth/verify-account")
                    .build();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
    }
    @PostMapping("/generate-verify-token") // Corrected line: removed the colon
    public ResponseEntity<?> generateVerifyToken(@RequestBody GenerateVerificationTokenRequest request) {
        try{
            CustomDataResponse response = CustomDataResponse.builder()
                    .data(verifyRegistrationService.generateVerificationToken(request))
                    .message("Token generated successfully")
                    .build();
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        catch (DataConflictException e){
            CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                    .code(GlobalErrorCodes.EMAIL_NOT_FOUND)
                    .message(e.getMessage())
                    .details("/api/auth/generate-verify-token")
                    .build();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse.toString());
        }
        catch (Exception e){
            CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                    .code(GlobalErrorCodes.INTERNAL_SERVER_ERROR)
                    .message(e.getMessage())
                    .details("/api/auth/generate-verify-token")
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse.toString());
        }

    }
}
