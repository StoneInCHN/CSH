package com.csh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.csh.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 新闻
 * 
 * @author tanbiao
 *
 */

@Entity
@Table(name = "csh_news")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_news_sequence")
public class News extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = 2728868626426836566L;

  private String title;

  private String content;

  /**
   * 发布人姓名
   */
  private String userName;

  /**
   * 是否推送提醒
   */
  private Boolean publishReminder;

  private NewsCategory newsCategory;

  @JsonProperty
  @Column(length=100,nullable=false)
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @JsonProperty
  @Column(length=2000,nullable=false)
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @JsonProperty
  @Column(length=50)
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
  
  @JsonProperty
  @Column(nullable=false)
  public Boolean getPublishReminder() {
    return publishReminder;
  }

  public void setPublishReminder(Boolean publishReminder) {
    this.publishReminder = publishReminder;
  }

  @JsonProperty
  @ManyToOne
  public NewsCategory getNewsCategory() {
    return newsCategory;
  }

  public void setNewsCategory(NewsCategory newsCategory) {
    this.newsCategory = newsCategory;
  }
  
}
