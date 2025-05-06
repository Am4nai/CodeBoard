package com.codeboard.codeboard_backend.controller;

import com.codeboard.codeboard_backend.dto.request.UserCreateDto;
import com.codeboard.codeboard_backend.dto.response.AuthResponseDto;
import com.codeboard.codeboard_backend.dto.response.UserResponseDto;
import com.codeboard.codeboard_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody UserCreateDto userCreateDto) {
        AuthResponseDto response = userService.registerUser(userCreateDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getCurrentUser(Authentication authentication) {
        // Получаем имя пользователя из контекста безопасности
        String username = authentication.getName();

        // Получаем данные пользователя через сервис
        UserResponseDto userResponseDto = userService.getUserByUsername(username);

        return ResponseEntity.ok(userResponseDto);
    }
}