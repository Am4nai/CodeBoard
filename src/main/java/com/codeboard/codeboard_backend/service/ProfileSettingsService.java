package com.codeboard.codeboard_backend.service;

import com.codeboard.codeboard_backend.dto.request.ProfileSettingsUpdateDto;
import com.codeboard.codeboard_backend.dto.response.ProfileSettingsResponseDto;
import com.codeboard.codeboard_backend.model.Profile;
import com.codeboard.codeboard_backend.model.Setting;
import com.codeboard.codeboard_backend.model.User;
import com.codeboard.codeboard_backend.model.enums.ThemeEnum;
import com.codeboard.codeboard_backend.repository.ProfileRepository;
import com.codeboard.codeboard_backend.repository.SettingRepository;
import com.codeboard.codeboard_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileSettingsService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final SettingRepository settingRepository;

    public ProfileSettingsResponseDto getProfileSettings(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Profile profile = profileRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        Setting settings = settingRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Settings not found"));

        return convertToDto(user, profile, settings);
    }

    public void updateProfileSettings(Long userId, ProfileSettingsUpdateDto request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Profile profile = profileRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        Setting settings = settingRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Settings not found"));

        // Обновляем данные профиля
        if (request.getBio() != null) profile.setBio(request.getBio());
        if (request.getAvatarUrl() != null) profile.setAvatarUrl(request.getAvatarUrl());
        if (request.getWebsite() != null) profile.setWebsite(request.getWebsite());

        // Обновляем настройки
        if (request.getTheme() != null) settings.setThemeEnum(ThemeEnum.valueOf(request.getTheme()));
        if (request.getNotificationsEnabled() != null) settings.setNotificationsEnabled(request.getNotificationsEnabled());

        profileRepository.save(profile);
        settingRepository.save(settings);
    }

    private ProfileSettingsResponseDto convertToDto(User user, Profile profile, Setting settings) {
        ProfileSettingsResponseDto dto = new ProfileSettingsResponseDto();
        dto.setUserId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setBio(profile.getBio());
        dto.setAvatarUrl(profile.getAvatarUrl());
        dto.setWebsite(profile.getWebsite());
        dto.setTheme(settings.getThemeEnum().name());
        dto.setNotificationsEnabled(settings.getNotificationsEnabled());
        return dto;
    }
}