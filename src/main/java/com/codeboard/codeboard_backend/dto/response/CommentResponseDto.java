package com.codeboard.codeboard_backend.dto.response;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class CommentResponseDto {

    private Long id;

    private String content;

    private String authorUsername;

    private LocalDateTime createdAt;
}