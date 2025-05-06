package com.codeboard.codeboard_backend.model.composite_keys;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PostRatingId implements Serializable {
    private Long postId;
    private Long userId;
}
