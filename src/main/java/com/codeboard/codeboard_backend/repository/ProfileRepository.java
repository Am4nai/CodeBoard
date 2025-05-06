package com.codeboard.codeboard_backend.repository;

import com.codeboard.codeboard_backend.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findById(Long id); // Поиск профиля по ID пользователя
}
