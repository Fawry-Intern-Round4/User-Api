package com.example.userapi.enums;

import lombok.Getter;

@Getter
public enum Messages {
    USER_NOT_FOUND("User not found"),
    ;
    final String message;
    Messages(String message) {
        this.message = message;
    }
}
