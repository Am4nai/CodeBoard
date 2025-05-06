package com.codeboard.codeboard_backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class FavoriteCreateDto {

    @NotBlank(message = "List name is required")
    @Size(min = 3, max = 100, message = "List name must be between 3 and 100 characters")
    private String listName;

    private Set<Long> postIds;
}