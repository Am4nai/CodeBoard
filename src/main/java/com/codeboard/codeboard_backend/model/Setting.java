package com.codeboard.codeboard_backend.model;

import com.codeboard.codeboard_backend.model.enums.ThemeEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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
    @Column(name = "theme", nullable = false, columnDefinition = "theme_enum DEFAULT 'LIGHT'")
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private ThemeEnum themeEnum = ThemeEnum.LIGHT;

    @Column(name = "notifications_enabled", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean notificationsEnabled = true;
}
