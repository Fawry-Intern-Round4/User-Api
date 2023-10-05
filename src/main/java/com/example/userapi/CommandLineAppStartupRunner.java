package com.example.userapi;

import com.example.userapi.dto.AuthDto;
import com.example.userapi.repository.UserRepository;
import com.example.userapi.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandLineAppStartupRunner implements CommandLineRunner {
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;
    @Value("${user.admin.username}")
    private String username;
    @Value("${user.admin.password}")
    private String password;

    @Override
    public void run(String... args) {
        if (userRepository.existsByUsername(username)) {
            return;
        }
        authenticationService.signUp(new AuthDto(username, password));
    }
}