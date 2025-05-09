package com.codeboard.codeboard_backend.dto.request;

import lombok.Data;

@Data
public class ProfileSettingsUpdateDto {
    private String bio;
    private String avatarUrl;
    private String website;
    private String theme;
    private Boolean notificationsEnabled;
}