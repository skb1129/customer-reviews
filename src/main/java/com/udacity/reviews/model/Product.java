package com.udacity.reviews.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SuryaKant
 * @date 11/8/19
 */
@Entity
@Table(name = "PRODUCT")
public class Product extends CommonEntityFields implements Serializable {

    @Column(name = "NAME")
    private String name;

    @Column(name = "COST")
    private long cost;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "PRODUCT_ID")
    private List<Review> reviewsList = new ArrayList<>();

    public Product() {
    }

    public Product(String name, long cost, String description) {
        this.name = name;
        this.cost = cost;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Review> getReviewsList() {
        return reviewsList;
    }

    public void setReviewsList(List<Review> reviewsList) {
        this.reviewsList = reviewsList;
    }
}
