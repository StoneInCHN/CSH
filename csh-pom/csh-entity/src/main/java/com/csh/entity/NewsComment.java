package com.csh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.csh.entity.base.BaseEntity;

/**
 * 新闻资讯评论
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "csh_news_comment")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_news_comment_sequence")
public class NewsComment extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = -8077031460970833774L;

  /**
   * 评论内容
   */
  private String content;

  /**
   * 评论用户
   */
  private String userName;

  /**
   * 评论用户头像
   */
  private String photoUrl;

  /**
   * 评论的资讯
   */
  private News news;


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @Column(length = 100)
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPhotoUrl() {
    return photoUrl;
  }

  public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
  }

  @ManyToOne
  public News getNews() {
    return news;
  }

  public void setNews(News news) {
    this.news = news;
  }



}
