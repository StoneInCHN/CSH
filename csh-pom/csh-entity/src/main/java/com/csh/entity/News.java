package com.csh.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

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

  /**
   * 标题
   */
  private String title;

  /**
   * 副标题
   */
  private String subTitle;

  private String content;

  /**
   * 发布人姓名
   */
  private String userName;

  /**
   * 是否推送提醒
   */
  private Boolean publishReminder;

  /**
   * 是否已经推送
   */
  private Boolean hasReminder;
  
  private NewsCategory newsCategory;

  /**
   * 新闻配图
   */
  private String imgUrl;

  private MultipartFile imgFile;
  
  /**
   * 是否启用
   */
  private Boolean isEnabled;

  /**
   * 阅读次数
   */
  private Integer readCounts = 0;

  /**
   * 点赞次数
   */
  private Integer likeCounts = 0;

  /**
   * 评论次数
   */
  private Integer commentCounts = 0;

  /**
   * 新闻评论
   */
  private Set<NewsComment> newsComments = new HashSet<NewsComment>();



  public String getSubTitle() {
    return subTitle;
  }

  public void setSubTitle(String subTitle) {
    this.subTitle = subTitle;
  }

  public String getImgUrl() {
    return imgUrl;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }

  @Column(nullable=false,columnDefinition="INT default 0")
  public Integer getReadCounts() {
    return readCounts;
  }

  public void setReadCounts(Integer readCounts) {
    this.readCounts = readCounts;
  }

  @Column(nullable=false,columnDefinition="INT default 0")
  public Integer getLikeCounts() {
    return likeCounts;
  }

  public void setLikeCounts(Integer likeCounts) {
    this.likeCounts = likeCounts;
  }

  public Integer getCommentCounts() {
    return commentCounts;
  }

  public void setCommentCounts(Integer commentCounts) {
    this.commentCounts = commentCounts;
  }

  @OneToMany(mappedBy = "news", cascade = CascadeType.ALL)
  public Set<NewsComment> getNewsComments() {
    return newsComments;
  }

  public void setNewsComments(Set<NewsComment> newsComments) {
    this.newsComments = newsComments;
  }

  public Boolean getIsEnabled() {
    return isEnabled;
  }

  public void setIsEnabled(Boolean isEnabled) {
    this.isEnabled = isEnabled;
  }

  @JsonProperty
  @Column(length = 100, nullable = false)
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @JsonProperty
  @Column(length = 2000, nullable = false)
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @JsonProperty
  @Column(length = 50)
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  @JsonProperty
  @Column(nullable = false)
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

  @Transient
  public MultipartFile getImgFile() {
    return imgFile;
  }

  public void setImgFile(MultipartFile imgFile) {
    this.imgFile = imgFile;
  }

  public Boolean getHasReminder() {
    return hasReminder;
  }

  public void setHasReminder(Boolean hasReminder) {
    this.hasReminder = hasReminder;
  }
  
}
