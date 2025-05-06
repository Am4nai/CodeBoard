package com.codeboard.codeboard_backend.repository;

import com.codeboard.codeboard_backend.model.PostRating;
import com.codeboard.codeboard_backend.model.composite_keys.PostRatingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRatingRepository extends JpaRepository<PostRating, PostRatingId> {
    @Query("SELECT AVG(pr.ratingValue) FROM PostRating pr WHERE pr.id.postId = :postId")
    Double calculateAverageRatingByPostId(@Param("postId") Long postId);
}
