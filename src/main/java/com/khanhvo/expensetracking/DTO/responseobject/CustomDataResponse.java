package com.khanhvo.expensetracking.DTO.responseobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data // Lombok annotation to generate getters and setters
@Builder // Lombok annotation to generate builder pattern for the object
public class CustomDataResponse<T> {
    @Builder.Default
    private LocalDateTime timestamp=LocalDateTime.now();
    private T data;
    private String message;
}
