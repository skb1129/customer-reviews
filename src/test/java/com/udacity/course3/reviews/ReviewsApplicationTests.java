package com.udacity.course3.reviews;

import com.udacity.course3.reviews.model.Comment;
import com.udacity.course3.reviews.model.Product;
import com.udacity.course3.reviews.model.Review;
import com.udacity.course3.reviews.repository.CommentsRepository;
import com.udacity.course3.reviews.repository.ProductsRepository;
import com.udacity.course3.reviews.repository.ReviewsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReviewsApplicationTests {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	ProductsRepository productsRepository;

	@Autowired
	ReviewsRepository reviewsRepository;

	@Autowired
	CommentsRepository commentsRepository;


	@Test
	public void contextLoads() {
		assertThat(dataSource).isNotNull();
		assertThat(jdbcTemplate).isNotNull();
		assertThat(entityManager).isNotNull();
		assertThat(productsRepository).isNotNull();
		assertThat(reviewsRepository).isNotNull();
		assertThat(commentsRepository).isNotNull();
	}

	@Test
	public void isProductLoaded(){
		Product product = productsRepository.findByNameAndCost("PIZZA", 100);
		assertThat(product).isNotNull();
		assertThat(product.getDescription()).isEqualTo("PIZZA");
	}

	@Test
	public void isReviewLoaded(){
		Product product = productsRepository.findByNameAndCost("PIZZA", 100);
		List<Review> review = reviewsRepository.findByProductId(product.getId());
		assertThat(review).isNotEmpty();
	}

	@Test
	public void isCommentLoaded(){
		List<Comment> comment = commentsRepository.findByReviewId(1);
		assertThat(comment).isNotEmpty();
	}

}