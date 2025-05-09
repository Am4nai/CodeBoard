package com.codeboard.codeboard_backend.dto.response;

import lombok.Data;

@Data
public class ProfileSettingsResponseDto {
    private Long userId;
    private String username;
    private String email;
    private String bio;
    private String avatarUrl;
    private String website;
    private String theme;
    private Boolean notificationsEnabled;
}