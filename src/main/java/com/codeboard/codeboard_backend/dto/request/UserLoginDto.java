package com.codeboard.codeboard_backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginDto {

    @NotBlank(message = "Username or email cannot be blank")
    private String usernameOrEmail;

    @NotBlank(message = "Password cannot be blank")
    private String password;
}