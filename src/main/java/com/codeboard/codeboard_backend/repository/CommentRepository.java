package com.codeboard.codeboard_backend.repository;

import com.codeboard.codeboard_backend.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByContentContainingIgnoreCase(String content); // Поиск комментариев по части текста
    List<Comment> findByUserId(Long userId);                         // Поиск комментариев пользователя
}
