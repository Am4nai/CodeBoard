package com.codeboard.codeboard_backend.service;

import com.codeboard.codeboard_backend.dto.request.UserCreateDto;
import com.codeboard.codeboard_backend.dto.request.UserUpdateDto;
import com.codeboard.codeboard_backend.dto.response.AuthResponseDto;
import com.codeboard.codeboard_backend.dto.response.UserAResponseDto;
import com.codeboard.codeboard_backend.dto.response.UserResponseDto;
import com.codeboard.codeboard_backend.model.User;
import com.codeboard.codeboard_backend.util.JwtUtils;
import com.codeboard.codeboard_backend.model.enums.UserRoleEnum;
import com.codeboard.codeboard_backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

        User user = new User();
        user.setUsername(userCreateDto.getUsername());
        user.setEmail(userCreateDto.getEmail());
        user.setPasswordHash(passwordEncoder.encode(userCreateDto.getPassword()));
        user.setUserRoleEnum(UserRoleEnum.USER);

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

    // 1. Получение списка пользователей с пагинацией
    public Page<UserAResponseDto> getUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> users = userRepository.findAll(pageable);
        return users.map(this::convertToDto);
    }

    // 2. Получение конкретного пользователя (по username)
    public UserAResponseDto getUserAByUsername(String username) {
        // Находим пользователя по username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Преобразуем сущность User в DTO
        return convertToDto(user);
    }

    // 3. Изменение данных пользователя
    public UserAResponseDto updateUser(Long id, UserUpdateDto request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (request.getUsername() != null) user.setUsername(request.getUsername());
        if (request.getEmail() != null) user.setEmail(request.getEmail());
        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        } else {
            // Пароль не передан, оставляем старое значение
            System.out.println("Password was not provided for user with ID: {}" + id);
        }
        if (request.getRole() != null) user.setUserRoleEnum(UserRoleEnum.valueOf(request.getRole()));

        userRepository.save(user);
        return convertToDto(user);
    }

    // Преобразование User в DTO
    private UserAResponseDto convertToDto(User user) {
        UserAResponseDto dto = new UserAResponseDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getUserRoleEnum().name());
        dto.setCreatedAt(user.getCreatedAt());
        return dto;
    }
}