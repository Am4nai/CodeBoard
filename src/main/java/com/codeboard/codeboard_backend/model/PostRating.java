package com.codeboard.codeboard_backend.model;

import com.codeboard.codeboard_backend.model.composite_keys.PostRatingId;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "post_ratings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostRating {

    @EmbeddedId
    private PostRatingId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("postId") // Связывает часть составного ключа с полем postId
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId") // Связывает часть составного ключа с полем userId
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "rating_value", nullable = false)
    @Min(value = 1, message = "Rating value must be at least 1")
    @Max(value = 5, message = "Rating value must not exceed 5")
    private Short ratingValue;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
}
