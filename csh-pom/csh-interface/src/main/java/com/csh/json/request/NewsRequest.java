package com.csh.json.request;

import com.csh.json.base.BaseRequest;

public class NewsRequest extends BaseRequest {

  /**
   * 新闻标题
   */
  private String title;

  /**
   * 新闻URL
   */
  private String contentUrl;

  /**
   * 类别ID
   */
  private Long categoryId;

  /**
   * 新闻ID
   */
  private Long newsId;

  /**
   * 评论内容
   */
  private String comment;

  /**
   * 点赞操作，点击：1，取消：-1
   */
  private Integer doLikeOpr;


  public Integer getDoLikeOpr() {
    return doLikeOpr;
  }

  public void setDoLikeOpr(Integer doLikeOpr) {
    this.doLikeOpr = doLikeOpr;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public Long getNewsId() {
    return newsId;
  }

  public void setNewsId(Long newsId) {
    this.newsId = newsId;
  }

  public Long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContentUrl() {
    return contentUrl;
  }

  public void setContentUrl(String contentUrl) {
    this.contentUrl = contentUrl;
  }

}
