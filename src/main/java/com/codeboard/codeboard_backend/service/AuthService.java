package com.codeboard.codeboard_backend.service;

import com.codeboard.codeboard_backend.dto.request.UserLoginDto;
import com.codeboard.codeboard_backend.dto.response.AuthResponseDto;
import com.codeboard.codeboard_backend.dto.response.UserResponseDto;
import com.codeboard.codeboard_backend.exception.AuthenticationException;
import com.codeboard.codeboard_backend.model.User;
import com.codeboard.codeboard_backend.repository.UserRepository;
import com.codeboard.codeboard_backend.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public AuthResponseDto login(UserLoginDto loginDto) {
        // Поиск пользователя по email
        User user = userRepository.findByEmail(loginDto.getUsernameOrEmail())
                .orElseThrow(() -> new AuthenticationException("Invalid credentials"));

        // Проверка пароля
        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPasswordHash())) {
            throw new AuthenticationException("Invalid credentials");
        }

        // Генерация JWT-токена
        String token = jwtUtils.generateToken(user.getUsername(), user);

        // Преобразование сущности в DTO
        UserResponseDto userResponseDto = convertToResponseDto(user);

        // Возвращаем токен и данные пользователя
        return new AuthResponseDto(token, userResponseDto);
    }

    private UserResponseDto convertToResponseDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getUserRoleEnum().name());
        dto.setCreatedAt(user.getCreatedAt());
        return dto;
    }
}