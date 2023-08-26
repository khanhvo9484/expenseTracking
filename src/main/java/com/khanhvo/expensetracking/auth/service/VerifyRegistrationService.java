package com.khanhvo.expensetracking.auth.service;

import com.khanhvo.expensetracking.auth.DTO.GenerateVerificationTokenRequest;
import com.khanhvo.expensetracking.exception.DataConflictException;
import com.khanhvo.expensetracking.model.AccountVerification;
import com.khanhvo.expensetracking.repository.UserRepository;
import com.khanhvo.expensetracking.repository.VerificationRepository;
import com.khanhvo.expensetracking.security.JwtService;
import com.khanhvo.expensetracking.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerifyRegistrationService {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final VerificationRepository verificationRepository;
    private final EmailSenderService emailSenderService;
    public String generateVerificationToken(GenerateVerificationTokenRequest request) {

            var user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                    () -> new DataConflictException("User not found")
            );
            var token = jwtService.generateToken(request.getEmail());
            try{
                emailSenderService.sendEmailWithTemplate(request.getEmail(), request.getFullName().trim(), token);
            }
            catch (Exception e){
                throw new RuntimeException("Error sending email");
            }
            var verification = AccountVerification.builder()
                    .userEmail(request.getEmail())
                    .token(token)
                    .build();
            try {
                verificationRepository.save(verification);
                return token;
            } catch (Exception e) {
                throw new RuntimeException("Error saving verification token");
            }

    }
    public AccountVerification verifyEmail(String token){
        var verification = verificationRepository.findTopByTokenOrderByCreatedAt(token).orElseThrow(
                () -> new DataConflictException("Invalid token")
        );
        if(verification.isVerified()){
            throw new DataConflictException("Email already verified");
        }
        var user = userRepository.findByEmail(verification.getUserEmail()).orElseThrow(
                () -> new DataConflictException("User not found")
        );
        if(verification.getExpiredAt().isBefore(java.time.LocalDateTime.now())){
            throw new DataConflictException("Token expired");
        }
        verificationRepository.updateVerificationStatus(token,true);
        userRepository.updateVerificationStatus(user.getEmail(),true);

        verification.setVerified(true);
        return verification;
    }
}
