package com.udacity.reviews.repository;

import com.udacity.reviews.model.Product;
import com.udacity.reviews.model.Review;
import com.udacity.reviews.model.ReviewDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author SuryaKant
 * @date 11/8/19
 */
public interface ReviewsMongoRepository extends MongoRepository<ReviewDocument, String> {
    List<Review> findByProduct(Product product);

    ReviewDocument findByDescriptionAndCount(String Description, long count);
}
