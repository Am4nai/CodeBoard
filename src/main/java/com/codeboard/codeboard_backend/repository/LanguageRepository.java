package com.codeboard.codeboard_backend.repository;

import com.codeboard.codeboard_backend.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language, Long> {
    Optional<Language> findByName(String name);     // Поиск языка программирования по названию
    boolean existsByName(String name);             // Проверка существования языка по названию
    Optional<Language> findByNameIgnoreCase(String name);
}
