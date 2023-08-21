package com.khanhvo.expensetracking.auth.service;

import com.khanhvo.expensetracking.auth.DTO.AuthenticationRequest;
import com.khanhvo.expensetracking.auth.DTO.AuthenticationResponse;
import com.khanhvo.expensetracking.auth.DTO.RegisterRequest;
import com.khanhvo.expensetracking.auth.exception.CustomAuthenticationException;
import com.khanhvo.expensetracking.auth.exception.CustomBadCredentialsException;
import com.khanhvo.expensetracking.errorcode.GlobalErrorCodes;
import com.khanhvo.expensetracking.exception.BadRequestException;
import com.khanhvo.expensetracking.security.JwtService;
import com.khanhvo.expensetracking.model.Role;
import com.khanhvo.expensetracking.model.User;
import com.khanhvo.expensetracking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var userOptional = userRepository.findByEmail(registerRequest.getEmail());
        if(userOptional.isPresent()) {
            throw new RuntimeException(GlobalErrorCodes.EMAIL_ALREADY_EXISTS);
        }
        var user = User.builder()
                .fullName(registerRequest.getFullName())
                .email(registerRequest.getEmail())
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .verified(false)
                .build();

        userRepository.save(user);
        var jwtToken =jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authRequest) {

        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );

            var user = userRepository.findByEmail(authRequest.getEmail()).orElseThrow();
            var jwtToken =jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }
        catch (CustomAuthenticationException e){
            throw new CustomBadCredentialsException(GlobalErrorCodes.BAD_CREDENTIALS);
        }
    }
}
