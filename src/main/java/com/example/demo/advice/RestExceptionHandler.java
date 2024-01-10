package com.example.demo.advice;

import com.example.demo.exception.DroneException;
import com.example.demo.dto.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorCode> handleValidationException(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body(new ErrorCode(new Date(),HttpStatus.BAD_REQUEST.value(),ex.getFieldError().getDefaultMessage()));
    }

    @ExceptionHandler(DroneException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorCode> handleValidationException(DroneException ex) {
        return ResponseEntity.badRequest().body(new ErrorCode(new Date(),HttpStatus.BAD_REQUEST.value(),ex.getMessage()));
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorCode> handleValidationException(Exception ex) {
        return ResponseEntity.badRequest().body(new ErrorCode(new Date(),HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()));
    }
}
