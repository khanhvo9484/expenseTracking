package com.khanhvo.expensetracking.auth.DTO;

import com.khanhvo.expensetracking.model.AccountVerification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountVerifyResponse {
    private String email;
    private boolean verified;

    public static AccountVerifyResponse mapToDto(AccountVerification accountVerification) {
        return AccountVerifyResponse.builder()
                .email(accountVerification.getUserEmail())
                .verified(accountVerification.isVerified())
                .build();

    }
}
