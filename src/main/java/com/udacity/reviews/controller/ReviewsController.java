package com.udacity.reviews.controller;

import com.udacity.reviews.model.Product;
import com.udacity.reviews.model.Review;
import com.udacity.reviews.model.ReviewDocument;
import com.udacity.reviews.repository.ProductsRepository;
import com.udacity.reviews.repository.ReviewsMongoRepository;
import com.udacity.reviews.repository.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with review entity.
 */
@RestController
public class ReviewsController {

    @Autowired
    ReviewsRepository reviewsRepository;

    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    ReviewsMongoRepository reviewsMongoRepository;

    /**
     * Creates a review for a product.
     *
     * @param productId The id of the product.
     * @return The created review or 404 if product id is not found.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.POST)
    public ResponseEntity<?> createReviewForProduct(@PathVariable("productId") Integer productId,
                                                    @RequestBody Review review) {
        Optional<Product> product = productsRepository.findById(productId);
        if (!product.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wrong product ID");
        }
        review.setProduct(product.get());
        ReviewDocument reviewDocument = new ReviewDocument();
        reviewDocument.setProduct(product.get());
        reviewDocument.setComments(Collections.EMPTY_LIST);
        reviewDocument.setCount(review.getCount());
        reviewDocument.setDescription(review.getDescription());
        reviewsMongoRepository.save(reviewDocument);
        return ResponseEntity.status(HttpStatus.OK).body(reviewsRepository.save(review));
    }

    /**
     * Lists reviews by product.
     *
     * @param productId   The id of the product.
     * @param docRequired if you require the data in Mongo
     * @return The list of reviews.
     */
    @RequestMapping(value = "/reviews/products/{productId}/{docRequired}", method = RequestMethod.GET)
    public ResponseEntity<List<?>> listReviewsForProduct(@PathVariable("productId") Integer productId,
                                                         @PathVariable(value = "docRequired", required = false) boolean docRequired) {
        Optional<Product> product = productsRepository.findById(productId);
        if (!product.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
        if (docRequired) {
            List<ReviewDocument> reviewDocuments = new ArrayList<>();
            reviewsRepository.findByProductId(productId).forEach(review -> {
                reviewDocuments.add(reviewsMongoRepository.findByDescriptionAndCount(review.getDescription(), review.getCount()));
            });
            return ResponseEntity.status(HttpStatus.OK).body(reviewDocuments);
        }
        return ResponseEntity.status(HttpStatus.OK).body(reviewsRepository.findByProductId(productId));
    }
}
