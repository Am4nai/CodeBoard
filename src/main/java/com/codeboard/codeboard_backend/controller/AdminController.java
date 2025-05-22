package com.codeboard.codeboard_backend.controller;

import com.codeboard.codeboard_backend.dto.request.UserUpdateDto;
import com.codeboard.codeboard_backend.dto.response.UserAResponseDto;
import com.codeboard.codeboard_backend.service.UserService;
import com.codeboard.codeboard_backend.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final JwtUtils jwtUtils;

    @GetMapping("/users")
    public ResponseEntity<Page<UserAResponseDto>> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader("Authorization") String authHeader) {

        // Проверяем роль пользователя
        if (!isNotModerator(authHeader)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        // Получаем список пользователей с пагинацией
        Page<UserAResponseDto> users = userService.getUsers(page, size);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<UserAResponseDto> getUserByUsername(
            @PathVariable String username,
            @RequestHeader("Authorization") String authHeader) {

        // Проверяем роль пользователя
        if (!isNotModerator(authHeader)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        // Получаем данные пользователя по username
        UserAResponseDto user = userService.getUserAByUsername(username);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserAResponseDto> updateUser(
            @PathVariable Long id,
            @RequestBody UserUpdateDto request,
            @RequestHeader("Authorization") String authHeader) {

        if (!isNotModerator(authHeader)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        UserAResponseDto updatedUser = userService.updateUser(id, request);
        return ResponseEntity.ok(updatedUser);
    }

    // Вспомогательный метод для проверки роли MODERATOR
    private boolean isNotModerator(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return false;
        }

        String token = authHeader.substring(7);
        String role = jwtUtils.getRoleFromToken(token);

        return "MODERATOR".equals(role);
    }
}
