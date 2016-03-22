package com.csh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.csh.entity.base.BaseEntity;

/**
 * 意见反馈
 * 
 * @author andrea
 *
 */
@Entity
@Table(name = "csh_feedback")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_feedback_sequence")
public class FeedBack extends BaseEntity {

  private static final long serialVersionUID = 7282926448628904157L;



  /** 反馈内容 */
  private String content;

  private EndUser endUser;


  @ManyToOne
  public EndUser getEndUser() {
    return endUser;
  }

  public void setEndUser(EndUser endUser) {
    this.endUser = endUser;
  }


  @Column(length = 100)
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


}
