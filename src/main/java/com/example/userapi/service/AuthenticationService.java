package com.example.userapi.service;


import com.example.userapi.dto.AuthRequest;
import com.example.userapi.dto.JwtAuthenticationResponse;
import com.example.userapi.dto.UserResponse;

public interface AuthenticationService {
    UserResponse signUp(AuthRequest request);

    JwtAuthenticationResponse signIn(AuthRequest request);

}
