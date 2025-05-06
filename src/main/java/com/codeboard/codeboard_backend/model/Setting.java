package com.codeboard.codeboard_backend.model;

import com.codeboard.codeboard_backend.model.enums.ThemeEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "settings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Setting {
    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId // Используется для маппинга внешнего ключа как первичного ключа
    @JoinColumn(name = "id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "theme", nullable = false, columnDefinition = "theme_enum DEFAULT 'light'")
    private ThemeEnum themeEnum = ThemeEnum.LIGHT;

    @Column(name = "notifications_enabled", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean notificationsEnabled = true;
}
