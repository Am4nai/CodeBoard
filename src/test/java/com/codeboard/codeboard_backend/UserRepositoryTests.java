package com.codeboard.codeboard_backend;

import com.codeboard.codeboard_backend.dto.request.UserCreateDto;
import com.codeboard.codeboard_backend.model.User;
import com.codeboard.codeboard_backend.model.enums.UserRoleEnum;
import com.codeboard.codeboard_backend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "spring.profiles.active=test")
class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testAddUserWithDto() {
        // Создание DTO для нового пользователя
        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setUsername("test_user");
        userCreateDto.setEmail("test@example.com");
        userCreateDto.setPassword("password123");

        // Преобразование DTO в сущность User
        User user = new User();
        user.setUsername(userCreateDto.getUsername());
        user.setEmail(userCreateDto.getEmail());
        user.setPasswordHash(hashPassword(userCreateDto.getPassword())); // Пример хэширования пароля
        user.setUserRoleEnum(UserRoleEnum.USER);

        // Сохранение пользователя
        userRepository.save(user);

        // Поиск пользователя по имени
        Optional<User> foundUser = userRepository.findByUsername("test_user");

        // Проверка, что пользователь найден
        assertTrue(foundUser.isPresent(), "Пользователь должен быть найден");

        // Проверка полей пользователя
        assertEquals("test_user", foundUser.get().getUsername(), "Имя пользователя должно совпадать");
        assertEquals("test@example.com", foundUser.get().getEmail(), "Email должен совпадать");
        assertEquals(UserRoleEnum.USER, foundUser.get().getUserRoleEnum(), "Роль должна быть USER");
    }

    // Пример метода для хэширования пароля
    private String hashPassword(String password) {
        // Здесь может быть логика хэширования, например, через BCrypt
        return password; // Упрощённый пример
    }
}