package com.udacity.reviews;

import com.udacity.reviews.model.Product;
import com.udacity.reviews.model.ReviewDocument;
import com.udacity.reviews.repository.ReviewsMongoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author SuryaKant
 * @date 13/8/19
 */
@RunWith(SpringRunner.class)
@DataMongoTest
public class ReviewApplicationMongoTests {

    @Autowired
    ReviewsMongoRepository reviewsMongoRepository;

    @Test
    public void test() throws Exception {
        ReviewDocument reviewDocument = new ReviewDocument();
        reviewDocument.setDescription("just description");
        reviewDocument.setCount(100);
        reviewDocument.setProduct(new Product());
        reviewDocument.setComments(Collections.EMPTY_LIST);
        reviewsMongoRepository.save(reviewDocument);

        assertThat(reviewsMongoRepository.findByDescriptionAndCount(reviewDocument.getDescription(), reviewDocument.getCount()).getDescription())
                .isEqualTo("just description");
    }

}
