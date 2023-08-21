package com.khanhvo.expensetracking.DTO.responseobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SimpleErrorResponse {
    private String field;
    private String message;

}
