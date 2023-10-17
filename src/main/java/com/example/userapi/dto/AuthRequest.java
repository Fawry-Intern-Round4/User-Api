package com.example.userapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class AuthRequest {
    @NotBlank(message = "username is mandatory")
    private String username;
    @NotBlank(message = "password is mandatory")
    private String password;
}
