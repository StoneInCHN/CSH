package com.csh.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.csh.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "csh_news_category")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_news_category_sequence")
public class NewsCategory extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = -8077031460970833774L;

  /**
   * 类型名称
   */
  private String name;

  /**
   * 描述
   */
  private String categoryDesc;

  private Set<News> newsInfo = new HashSet<News>();

  /**
   * 是否启用
   */
  private Boolean isEnabled;


  public Boolean getIsEnabled() {
    return isEnabled;
  }

  public void setIsEnabled(Boolean isEnabled) {
    this.isEnabled = isEnabled;
  }

  @JsonProperty
  @Column(length = 30, nullable = false)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(length = 300)
  public String getCategoryDesc() {
    return categoryDesc;
  }

  public void setCategoryDesc(String categoryDesc) {
    this.categoryDesc = categoryDesc;
  }

  @JsonProperty
  @OneToMany(mappedBy = "newsCategory")
  public Set<News> getNewsInfo() {
    return newsInfo;
  }

  public void setNewsInfo(Set<News> newsInfo) {
    this.newsInfo = newsInfo;
  }

}
