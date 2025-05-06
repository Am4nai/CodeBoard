package com.codeboard.codeboard_backend.repository;

import com.codeboard.codeboard_backend.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByUserId(Long userId); // Поиск избранных списков пользователя
}
