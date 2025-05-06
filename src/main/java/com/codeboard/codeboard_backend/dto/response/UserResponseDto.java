package com.codeboard.codeboard_backend.dto.response;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class UserResponseDto {

    private Long id;

    private String username;

    private String email;

    private String role;

    private LocalDateTime createdAt;
}