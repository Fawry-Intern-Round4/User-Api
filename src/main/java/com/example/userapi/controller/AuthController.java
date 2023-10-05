package com.example.userapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class AuthController {
    @GetMapping("token/validation")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Void> validateToken() {
        return ResponseEntity.ok().build();
    }
}
