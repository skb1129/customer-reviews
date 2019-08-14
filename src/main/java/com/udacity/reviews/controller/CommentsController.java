package com.udacity.reviews.controller;

import com.udacity.reviews.model.Comment;
import com.udacity.reviews.model.Review;
import com.udacity.reviews.model.ReviewDocument;
import com.udacity.reviews.repository.CommentsRepository;
import com.udacity.reviews.repository.ReviewsMongoRepository;
import com.udacity.reviews.repository.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Spring REST controller for working with comment entity.
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    CommentsRepository commentsRepository;

    @Autowired
    ReviewsRepository reviewsRepository;

    @Autowired
    ReviewsMongoRepository reviewsMongoRepository;

    /**
     * Creates a comment for a review.
     * <p>
     * 1. Add argument for comment entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, save comment.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.POST)
    public ResponseEntity<?> createCommentForReview(@PathVariable("reviewId") Integer reviewId,
                                                    @RequestBody Comment comment) {
        Optional<Review> review = reviewsRepository.findById(reviewId);
        if (!review.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wrong review ID");
        }
        ReviewDocument reviewDocument = reviewsMongoRepository.findByDescriptionAndCount(review.get().getDescription(),
                review.get().getCount());
        if (Objects.nonNull(reviewDocument)) {
            reviewDocument.setComments(Arrays.asList(comment));
            reviewsMongoRepository.save(reviewDocument);
        }
        comment.setReview(review.get());
        return ResponseEntity.status(HttpStatus.OK).body(commentsRepository.save(comment));
    }

    /**
     * List comments for a review.
     * <p>
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, return list of comments.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.GET)
    public ResponseEntity<List<?>> listCommentsForReview(@PathVariable("reviewId") Integer reviewId) {
        Optional<Review> review = reviewsRepository.findById(reviewId);
        if (!review.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
        return ResponseEntity.status(HttpStatus.OK).body(commentsRepository.findByReviewId(reviewId));
    }
}
