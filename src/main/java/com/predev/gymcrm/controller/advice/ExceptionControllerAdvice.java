package com.predev.gymcrm.controller.advice;

import com.predev.gymcrm.exception.JwtException;
import com.predev.gymcrm.exception.RequestInLimitTimeException;
import com.predev.gymcrm.exception.ValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(ValidException.class)
    public ResponseEntity<?> validException(ValidException e) {
        return ResponseEntity.badRequest().body(e.getErrorMap());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> badCredentialsException(BadCredentialsException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> UsernameNotFoundException(UsernameNotFoundException e) {
        return ResponseEntity.badRequest().body(Map.of("error","사용자가 존재하지 않습니다"));
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<?> JwtException(JwtException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    @ExceptionHandler(RequestInLimitTimeException.class)
    public ResponseEntity<?> RequestInLimitTimeException(RequestInLimitTimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}