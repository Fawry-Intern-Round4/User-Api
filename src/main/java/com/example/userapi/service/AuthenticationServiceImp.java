package com.example.userapi.service;

import com.example.userapi.auth.JwtUtil;
import com.example.userapi.dto.AuthRequest;
import com.example.userapi.dto.JwtAuthenticationResponse;
import com.example.userapi.dto.UserResponse;
import com.example.userapi.entity.User;
import com.example.userapi.enums.Messages;
import com.example.userapi.enums.Role;
import com.example.userapi.mapper.UserMapper;
import com.example.userapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImp implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    @Override
    public UserResponse signUp(AuthRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .enable(true)
                .build();
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException(Messages.USER_ALREADY_EXISTS.getMessage());
        }
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    @Override
    public JwtAuthenticationResponse signIn(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException(Messages.INVALID_USERNAME_OR_PASSWORD.getMessage()));
        String jwt = jwtUtil.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

}
