package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author SuryaKant
 * @date 11/8/19
 */
public interface ReviewsRepository extends JpaRepository<Review, Integer> {
  List<Review> findByProductId(Integer productId);
  Review findByDescriptionAndCount(String Description, long count);
}
