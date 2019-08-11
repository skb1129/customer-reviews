package com.udacity.course3.reviews.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author SuryaKant
 * @date 11/8/19
 */
@Entity
@Table(name = "COMMENT")
public class Comment extends CommonEntityFields implements Serializable {

  @Column(name = "FEEDBACK")
  private String feedback;

  @Column(name = "LIKES")
  private long likes;

  @Column(name = "DISLIKES")
  private long dislikes;

  @Column(name = "REVIEW_ID")
  private Integer reviewId;

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

  public Integer getReviewId() {
    return reviewId;
  }

  public void setReviewId( Integer reviewId ) {
    this.reviewId = reviewId;
  }
}
