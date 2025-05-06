package com.codeboard.codeboard_backend.repository;

import com.codeboard.codeboard_backend.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTitleContainingIgnoreCase(String title); // Поиск постов по части заголовка (без учёта регистра)
    List<Post> findByUserId(Long userId);                     // Поиск постов пользователя
    List<Post> findByLanguageId(Long languageId);             // Поиск постов по языку программирования
    Page<Post> findAll(Pageable pageable);                    // Пагинация всех постов
}
