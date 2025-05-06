package com.codeboard.codeboard_backend.model;

import com.codeboard.codeboard_backend.model.composite_keys.PostLikeId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "post_likes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostLike {
    @EmbeddedId
    private PostLikeId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("postId")
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
