package com.example.userapi.controller;

import io.jsonwebtoken.JwtException;
import jakarta.security.auth.message.AuthException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionsHandlerController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handelGeneralException(Exception ex) {
        CustomError error = new CustomError(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(error.toString());
    }

    @ExceptionHandler({AuthenticationException.class, AuthException.class, JwtException.class})
    public ResponseEntity<String> handelIllegalArgumentException(Exception ex) {
        CustomError error = new CustomError(ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).contentType(MediaType.APPLICATION_JSON).body(error.toString());
    }

    @ExceptionHandler({UsernameNotFoundException.class, IllegalArgumentException.class})
    public ResponseEntity<String> handelNotFoundException(Exception ex) {
        CustomError error = new CustomError(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(error.toString());
    }

    private record CustomError(String message) {
        @Override
        public String toString() {
            return "{\"message\" : \"" + this.message + "\"}";
        }
    }
}
