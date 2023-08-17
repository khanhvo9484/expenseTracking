package com.khanhvo.expensetracking.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // This is a REST controller
@RequestMapping("/api/auth") // This is the path to the controller
@RequiredArgsConstructor // This is a Lombok annotation that creates a constructor with all required fields
public class AuthController {
    private final AuthenticationService authenticationService;
    @PostMapping("/register") // This is the path to the method
    public ResponseEntity<AuthenticationResponse>signup(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @PostMapping("authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(authRequest));
    }
}
