package com.example.userapi.enums;

import lombok.Getter;

@Getter
public enum Messages {
    USER_NOT_FOUND("User not found"),
    USER_ALREADY_EXISTS("User already exists"),
    USER_NOT_ENABLE("User not enable"),
    AUTHORIZATION_HEADER_NOT_FOUND("Authorization header not found"),
    USERNAME_NOT_FOUND_IN_TOKEN("Username not found in token"),
    INVALID_USERNAME_OR_PASSWORD("Invalid username or password"),
    ;
    final String message;

    Messages(String message) {
        this.message = message;
    }
}
