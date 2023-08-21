package com.khanhvo.expensetracking.DTO.responseobject;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder // Lombok annotation to generate builder pattern for the object
public class CustomErrorResponse {
    @Builder.Default
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS")
    private LocalDateTime timestamp= LocalDateTime.now();
    private String code;
    private String message;
    private String details;
    private String hint;

    @JsonIgnore
    public String getFormattedTimeStamp() {
        return timestamp.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
