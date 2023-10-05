package com.example.userapi.service;

import com.example.userapi.dto.UserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    UserDetailsService userDetailsService();
    List<UserResponse> findAllUsers();

    UserResponse deactivateUser(Long id);

    UserResponse activateUser(Long id);
}
