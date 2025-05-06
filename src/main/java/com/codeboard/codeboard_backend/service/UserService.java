package com.codeboard.codeboard_backend.service;

import com.codeboard.codeboard_backend.dto.request.UserCreateDto;
import com.codeboard.codeboard_backend.dto.response.AuthResponseDto;
import com.codeboard.codeboard_backend.dto.response.UserResponseDto;
import com.codeboard.codeboard_backend.model.User;
import com.codeboard.codeboard_backend.util.JwtUtils;
import com.codeboard.codeboard_backend.model.enums.UserRoleEnum;
import com.codeboard.codeboard_backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public AuthResponseDto registerUser(UserCreateDto userCreateDto) {
        // Проверка уникальности username
        if (userRepository.existsByUsername(userCreateDto.getUsername())) {
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }

        // Проверка уникальности email
        if (userRepository.existsByEmail(userCreateDto.getEmail())) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }

        // Создание нового пользователя
        User user = new User();
        user.setUsername(userCreateDto.getUsername());
        user.setEmail(userCreateDto.getEmail());
        user.setPasswordHash(passwordEncoder.encode(userCreateDto.getPassword()));
        user.setUserRoleEnum(UserRoleEnum.USER); // Устанавливаем роль по умолчанию

        // Сохранение пользователя в базу данных
        User savedUser = userRepository.save(user);

        // Генерация JWT-токена
        String token = jwtUtils.generateToken(savedUser.getUsername(), user);

        // Преобразование сущности в DTO
        UserResponseDto userResponseDto = convertToResponseDto(savedUser);

        // Возвращаем токен и данные пользователя
        return new AuthResponseDto(token, userResponseDto);
    }

    public UserResponseDto getUserByUsername(String username) {
        // Находим пользователя в базе данных
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Преобразуем сущность User в DTO
        return convertToResponseDto(user);
    }

    // Вспомогательный метод для преобразования сущности в DTO
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