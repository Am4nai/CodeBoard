package com.codeboard.codeboard_backend.repository;

import com.codeboard.codeboard_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username); // Поиск пользователя по имени
    Optional<User> findByEmail(String email);       // Поиск пользователя по email
    boolean existsByUsername(String username);       // Проверка существования пользователя по имени
    boolean existsByEmail(String email);            // Проверка существования пользователя по email
}
