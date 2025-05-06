package com.codeboard.codeboard_backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class PostCreateDto {

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;

    @NotBlank(message = "Content is required")
    @Size(min = 10, message = "Content must be at least 10 characters")
    private String content;

    private Set<Long> tagIds; // Идентификаторы тегов

    private Long languageId; // Идентификатор языка программирования

    private Long userId; // Идентификатор автора (пользователя)

    private String visibility; // Видимость поста (например, "PUBLIC", "PRIVATE")
}