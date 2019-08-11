package com.udacity.course3.reviews.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SuryaKant
 * @date 11/8/19
 */
@Entity
@Table(name = "REVIEW")
public class Review extends CommonEntityFields implements Serializable {

  @Column(name = "COUNT")
  private long count;

  @Column(name = "PRODUCT_ID")
  private Integer productId;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "REVIEW_ID")
  private List<Comment> commentList = new ArrayList<>();

  public long getCount() {
    return count;
  }

  public void setCount( long count ) {
    this.count = count;
  }

  public Integer getProductId() {
    return productId;
  }

  public void setProductId( Integer productId ) {
    this.productId = productId;
  }

  public List<Comment> getCommentList() {
    return commentList;
  }

  public void setCommentList( List<Comment> commentList ) {
    this.commentList = commentList;
  }
}
