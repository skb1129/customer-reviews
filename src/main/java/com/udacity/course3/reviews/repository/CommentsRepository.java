package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author SuryaKant
 * @date 11/8/19
 */
public interface CommentsRepository extends JpaRepository<Comment, Integer> {
  List<Comment> findByReviewId( Integer reviewId);
  Comment findByFeedbackAndLikes(String feedBack, long likes);
}
