package com.codeboard.codeboard_backend.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentListDto {

    private Long id;

    private String content;

    private String authorUsername;

    private LocalDateTime createdAt;
}