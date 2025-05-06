package com.codeboard.codeboard_backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TagCreateDto {

    @NotBlank(message = "Tag name is required")
    @Size(min = 3, max = 50, message = "Tag name must be between 3 and 50 characters")
    private String name;
}