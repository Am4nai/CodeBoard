package com.codeboard.codeboard_backend.repository;

import com.codeboard.codeboard_backend.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByName(String name);   // Поиск тега по названию
    boolean existsByName(String name);      // Проверка существования тега по названию
    Optional<Tag> findByNameIgnoreCase(String name);
}
