package com.codeboard.codeboard_backend.dto.request;

import lombok.Data;

@Data
public class UserUpdateDto {
    private String username;
    private String email;
    private String password;
    private String role;
}