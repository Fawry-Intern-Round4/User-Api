package com.example.userapi.controller;

import com.example.userapi.dto.AuthRequest;
import com.example.userapi.dto.JwtAuthenticationResponse;
import com.example.userapi.dto.UserResponse;
import com.example.userapi.service.AuthenticationService;
import com.example.userapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserResponse signUp(@Valid @RequestBody AuthRequest request) {
        return authenticationService.signUp(request);
    }

    @PostMapping("login")
    @ResponseStatus(code = HttpStatus.OK)
    public JwtAuthenticationResponse login(@Valid @RequestBody AuthRequest request) {
        return authenticationService.signIn(request);
    }

    @PutMapping("activation/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public UserResponse activateUser(@PathVariable("id") Long id) {
        return userService.activateUser(id);
    }

    @PutMapping("deactivation/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public UserResponse deactivateUser(@PathVariable("id") Long id) {
        return userService.deactivateUser(id);
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<UserResponse> findAllUsers() {
        return userService.findAllUsers();
    }

}
