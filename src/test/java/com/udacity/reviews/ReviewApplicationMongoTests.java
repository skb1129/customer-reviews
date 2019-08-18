package com.udacity.reviews;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.udacity.reviews.model.Product;
import com.udacity.reviews.model.ReviewDocument;
import com.udacity.reviews.repository.ReviewsMongoRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Shubham Sharma
 * @date 13/8/19
 */
@RunWith(SpringRunner.class)
@DataMongoTest
public class ReviewApplicationMongoTests {

    @Autowired
    ReviewsMongoRepository reviewsMongoRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    public void MongoTemplateTest() {
        DBObject objectToSave = BasicDBObjectBuilder.start()
                .add("key", "value")
                .get();

        mongoTemplate.save(objectToSave, "collection");
        assertThat(mongoTemplate.findAll(DBObject.class, "collection")).isNotEmpty();

    }

    @Test
    public void test() throws Exception {
        Product product = new Product();
        product.setId(1);
        ReviewDocument reviewDocument = new ReviewDocument();
        reviewDocument.setDescription("just description");
        reviewDocument.setCount(100);
        reviewDocument.setProduct(product);
        reviewDocument.setComments(Collections.EMPTY_LIST);
        reviewsMongoRepository.save(reviewDocument);

        Assertions.assertThat(reviewsMongoRepository.findByDescriptionAndCount(reviewDocument.getDescription(), reviewDocument.getCount()).getDescription())
                .isEqualTo("just description");
    }

}
