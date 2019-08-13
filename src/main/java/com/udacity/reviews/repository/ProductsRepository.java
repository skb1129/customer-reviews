package com.udacity.reviews.repository;

import com.udacity.reviews.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author SuryaKant
 * @date 11/8/19
 */
public interface ProductsRepository extends JpaRepository<Product, Integer> {
    Product findByNameAndCost(String name, long cost);
}
