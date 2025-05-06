package com.codeboard.codeboard_backend.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SearchResultDto {

    private Long postId;

    private String title;

    private String contentSnippet;

    private String authorUsername;

    private List<String> tags;

    private Integer likesCount;

    private Double averageRating;

    private LocalDateTime createdAt;
}