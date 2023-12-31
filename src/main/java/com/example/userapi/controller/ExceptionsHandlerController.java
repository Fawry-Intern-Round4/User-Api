package com.example.userapi.controller;

import com.example.userapi.error.GeneralError;
import io.jsonwebtoken.JwtException;
import jakarta.security.auth.message.AuthException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Objects;


@ControllerAdvice
public class ExceptionsHandlerController {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody GeneralError handelGeneralException(Exception e) {
        return GeneralError.generateGeneralError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    @ExceptionHandler({AuthenticationException.class, AuthException.class, JwtException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public @ResponseBody GeneralError handelIllegalArgumentException(Exception e) {
        return GeneralError.generateGeneralError(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
    }

    @ExceptionHandler({UsernameNotFoundException.class, IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody GeneralError handelNotFoundException(Exception e) {
        return GeneralError.generateGeneralError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody GeneralError handleConstraintViolationException(ConstraintViolationException e) {
        String message = ((ConstraintViolation<?>) e.getConstraintViolations().toArray()[0]).getMessage();
        return GeneralError.generateGeneralError(HttpStatus.BAD_REQUEST.value(), message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody GeneralError handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
        return GeneralError.generateGeneralError(HttpStatus.BAD_REQUEST.value(), message);
    }
}
