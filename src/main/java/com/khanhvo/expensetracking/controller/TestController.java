package com.khanhvo.expensetracking.controller;

import com.khanhvo.expensetracking.DTO.requestDTO.TestRequest;
import com.khanhvo.expensetracking.DTO.responseobject.ValidationError;
import com.khanhvo.expensetracking.exception.BadRequestException;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/test")
public class TestController {
    @PostMapping("/hello")
    public String helloPost( @RequestBody @Valid TestRequest testRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
//            List<String> errorMessages = bindingResult.getAllErrors()
//                    .stream()
//                    .map(ObjectError::getDefaultMessage)
//                    .collect(Collectors.toList());
            List<ValidationError> validationErrors = bindingResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> new ValidationError(fieldError.getField(), fieldError.getDefaultMessage()))
                    .collect(Collectors.toList());
                throw new BadRequestException(validationErrors);
        }
//        try{
//
//        }
//        catch (Exception e) {
//
//            return "Error: " + e.getMessage();
//        }
        System.out.println("helloPost");
        return "Hello World12314124";
    }

//    @GetMapping("/hello")
//    public String hello() {
//        try {
//            var a = 1;
//            var b=a/0;
//            return "Hello World";
//        }
//        catch (Exception e) {
//            return "Error: " + e.getMessage();
//        }
////        return "Hello World";
//    }
}
