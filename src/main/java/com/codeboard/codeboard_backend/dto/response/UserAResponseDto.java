package com.codeboard.codeboard_backend.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserAResponseDto {
    private Long id;
    private String username;
    private String email;
    private String role;
    private LocalDateTime createdAt;
}