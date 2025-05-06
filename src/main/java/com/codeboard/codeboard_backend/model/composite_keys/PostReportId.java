package com.codeboard.codeboard_backend.model.composite_keys;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.*;
import java.io.Serializable;


@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PostReportId implements Serializable {
    private Long postId;
    private Long userId;
    private Long reportId;
}
