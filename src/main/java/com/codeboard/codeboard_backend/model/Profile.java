package com.codeboard.codeboard_backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId // Используется для маппинга внешнего ключа как первичного ключа
    @JoinColumn(name = "id", nullable = false)
    private User user;

    @Column(name = "bio", nullable = false, columnDefinition = "TEXT")
    private String bio;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "website")
    private String website;
}
