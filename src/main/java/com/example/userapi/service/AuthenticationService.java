package com.example.userapi.service;


import com.example.userapi.dto.AuthDto;
import com.example.userapi.dto.JwtAuthenticationResponse;
import com.example.userapi.dto.UserResponse;

public interface AuthenticationService {
    UserResponse signUp(AuthDto request);

    JwtAuthenticationResponse signIn(AuthDto request);

    Boolean validate(JwtAuthenticationResponse request);
}
