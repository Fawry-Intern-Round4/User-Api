package com.example.userapi.controller;

import com.example.userapi.error.GeneralError;
import io.jsonwebtoken.JwtException;
import jakarta.security.auth.message.AuthException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class ExceptionsHandlerController {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public GeneralError handelGeneralException(Exception e) {
        return new GeneralError(e.getMessage());
    }

    @ExceptionHandler({AuthenticationException.class, AuthException.class, JwtException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public GeneralError handelIllegalArgumentException(Exception e) {
        return new GeneralError(e.getMessage());
    }

    @ExceptionHandler({UsernameNotFoundException.class, IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public GeneralError handelNotFoundException(Exception e) {
        return new GeneralError(e.getMessage());
    }
}
