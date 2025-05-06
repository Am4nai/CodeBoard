package com.codeboard.codeboard_backend.model;

import com.codeboard.codeboard_backend.model.composite_keys.PostReportId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "post_reports")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostReport {

    @EmbeddedId
    private PostReportId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("postId") // Связывает часть составного ключа с полем postId
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId") // Связывает часть составного ключа с полем userId
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("reportId") // Связывает часть составного ключа с полем reportId
    @JoinColumn(name = "report_id", nullable = false)
    private Report report;
}
