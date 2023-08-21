package com.khanhvo.expensetracking.exceptionhandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.khanhvo.expensetracking.DTO.responseobject.CustomErrorResponse;
import com.khanhvo.expensetracking.DTO.responseobject.SimpleErrorResponse;
import com.khanhvo.expensetracking.DTO.responseobject.ValidationError;
import com.khanhvo.expensetracking.errorcode.GlobalErrorCodes;
import com.khanhvo.expensetracking.exception.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException ex) {
//        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
//                .code(GlobalErrorCodes.BAD_REQUEST)
//                .message(ex.getMessage())
//                .build();
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(errorResponse);

//        List<SimpleErrorResponse> errors = ex.getErrorMessages()
//                .stream()
//                .map(errorMessage -> SimpleErrorResponse.builder()
//                        .field("validation")
//                        .message(errorMessage)
//                        .build())
//                .collect(Collectors.toList());
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(errors);


        List<ValidationError> validationErrors = ex.getValidationErrors();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(validationErrors);

    }
}
