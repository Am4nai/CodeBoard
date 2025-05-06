package com.codeboard.codeboard_backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentCreateDto {

    @NotBlank(message = "Content is required")
    @Size(min = 3, message = "Content must be at least 3 characters")
    private String content;

    private Long postId;
}