package com.udacity.course3.reviews.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author SuryaKant
 * @date 11/8/19
 */
@Entity
@Table(name = "COMMENT")
@JsonIgnoreProperties("review")
public class Comment extends CommonEntityFields implements Serializable {

  @Column(name = "FEEDBACK")
  private String feedback;

  @Column(name = "LIKES")
  private long likes;

  @Column(name = "DISLIKES")
  private long dislikes;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "REVIEW_ID", nullable = false)
  private Review review;

  public Review getReview() {
    return review;
  }

  public void setReview( Review review ) {
    this.review = review;
  }

  public String getFeedback() {
    return feedback;
  }

  public void setFeedback( String feedback ) {
    this.feedback = feedback;
  }

  public long getLikes() {
    return likes;
  }

  public void setLikes( long likes ) {
    this.likes = likes;
  }

  public long getDislikes() {
    return dislikes;
  }

  public void setDislikes( long dislikes ) {
    this.dislikes = dislikes;
  }
}
