package com.example.kyc.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleExceptions(MethodArgumentNotValidException ex){
//        List<String> errors = new ArrayList<>();
        Map<String,String> errors = new HashMap<>();
//        errors.add(ex.getFieldError());
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(),error.getDefaultMessage());
        }
        return ResponseEntity.ok(errors);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<List<String>> handleExcp(RuntimeException ex){
        List<String> errors = new ArrayList<>();
        if(ex.getMessage().equals("Aadhar already exists")){
           errors.add("Aadhar already exists");
        }
        return ResponseEntity.ok(errors);
    }
}
