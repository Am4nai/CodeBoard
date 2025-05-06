package com.codeboard.codeboard_backend.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class PostResponseDto {

    private Long id;

    private String title;

    private String content;

    private String authorUsername;

    private String languageName;

    private List<String> tags;

    private String visibility;

    private LocalDateTime createdAt;
}