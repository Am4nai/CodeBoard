package com.codeboard.codeboard_backend.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostDetailsDto {

    private Long id;

    private String title;

    private String content;

    private String authorUsername;

    private List<String> tags;

    private List<CommentResponseDto> comments;

    private Integer likesCount;

    private Double averageRating;

    private String visibility;

    private LocalDateTime createdAt;
}