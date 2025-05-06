package com.codeboard.codeboard_backend.dto.response;

import lombok.Data;

@Data
public class FavoriteResponseDto {

    private Long id;

    private String listName;

    private Integer postCount;
}