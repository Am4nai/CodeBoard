package com.codeboard.codeboard_backend.repository;

import com.codeboard.codeboard_backend.model.PostLike;
import com.codeboard.codeboard_backend.model.composite_keys.PostLikeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, PostLikeId> {
    boolean existsByPostIdAndUserId(Long postId, Long userId); // Проверка, поставил ли пользователь лайк
    long countByPostId(Long postId);                          // Подсчёт количества лайков для поста
    void deleteByPostIdAndUserId(Long postId, Long userId);   // Удаление лайка пользователя для поста
}
