package com.codeboard.codeboard_backend.repository;

import com.codeboard.codeboard_backend.model.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SettingRepository extends JpaRepository<Setting, Long> {
 Optional<Setting> findById(Long id); // Поиск настроек по ID пользователя
}
