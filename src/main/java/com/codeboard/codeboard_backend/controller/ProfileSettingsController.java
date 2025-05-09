package com.codeboard.codeboard_backend.controller;

import com.codeboard.codeboard_backend.dto.request.ProfileSettingsUpdateDto;
import com.codeboard.codeboard_backend.dto.response.ProfileSettingsResponseDto;
import com.codeboard.codeboard_backend.service.ProfileSettingsService;
import com.codeboard.codeboard_backend.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile-settings")
@RequiredArgsConstructor
public class ProfileSettingsController {

    private final ProfileSettingsService profileSettingsService;
    private final JwtUtils jwtUtils;

    @GetMapping("/{userId}")
    public ResponseEntity<ProfileSettingsResponseDto> getProfileSettings(
            @PathVariable Long userId,
            @RequestHeader("Authorization") String authHeader) {

        // Убираем префикс "Bearer " из заголовка
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String token = authHeader.substring(7);

        // Извлекаем ID пользователя из токена
        Long tokenId = jwtUtils.getIdFromToken(token);

        // Извлекаем роль пользователя из токена
        String userRole = jwtUtils.getRoleFromToken(token);

        // Проверяем, совпадает ли ID из токена с ID из URL
        // или является ли пользователь MODERATOR
        if (!tokenId.equals(userId) && !"MODERATOR".equals(userRole)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Запрет доступа
        }

        // Получаем данные профиля
        ProfileSettingsResponseDto response = profileSettingsService.getProfileSettings(userId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateProfileSettings(
            @PathVariable Long userId,
            @RequestBody ProfileSettingsUpdateDto request,
            @RequestHeader("Authorization") String authHeader) {

        // Убираем префикс "Bearer " из заголовка
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String token = authHeader.substring(7);

        String userRole = jwtUtils.getRoleFromToken(token);

        // Извлекаем ID пользователя из токена
        Long tokenId = jwtUtils.getIdFromToken(token);

        // Проверяем, совпадает ли ID из токена с ID из URL
        if (!tokenId.equals(userId) && !"MODERATOR".equals(userRole)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Запрет доступа
        }

        // Обновляем данные профиля
        profileSettingsService.updateProfileSettings(userId, request);
        return ResponseEntity.noContent().build();
    }
}