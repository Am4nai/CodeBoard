package com.codeboard.codeboard_backend.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostListDto {

    private Long id;

    private String title;

    private String authorUsername;

    private Integer likesCount;

    private Double averageRating;

    private LocalDateTime createdAt;
}