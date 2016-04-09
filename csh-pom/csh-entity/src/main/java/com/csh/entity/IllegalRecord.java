package com.csh.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.csh.entity.base.BaseEntity;

/**
 * 违章记录
 * 
 * @author shijun
 *
 */
@Entity
@Table(name = "illegalRecord")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "illegal_record_sequence")
public class IllegalRecord extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = 1426841672720921821L;

  /**
   * 违章号
   */
  private String illegalId;

  /**
   * 处理地点
   */
  private String processingSite;

  /**
   * 扣分
   */
  private Integer score;

  /**
   * 罚款金额
   */
  private Double finesAmount;

  /**
   * 违章描述
   */
  private String illegalContent;

  /**
   * 违章地点
   */
  private String illegalAddress;

  /**
   * 车牌
   */
  private String plate;

  /**
   * 违章时间
   */
  private Date illegalDate;

  public String getIllegalId() {
    return illegalId;
  }

  public void setIllegalId(String illegalId) {
    this.illegalId = illegalId;
  }

  @Column(length = 200)
  public String getProcessingSite() {
    return processingSite;
  }

  public void setProcessingSite(String processingSite) {
    this.processingSite = processingSite;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  public Double getFinesAmount() {
    return finesAmount;
  }

  public void setFinesAmount(Double finesAmount) {
    this.finesAmount = finesAmount;
  }

  @Column(length = 400)
  public String getIllegalContent() {
    return illegalContent;
  }

  public void setIllegalContent(String illegalContent) {
    this.illegalContent = illegalContent;
  }

  @Column(length = 200)
  public String getIllegalAddress() {
    return illegalAddress;
  }

  public void setIllegalAddress(String illegalAddress) {
    this.illegalAddress = illegalAddress;
  }

  @Column(length = 2)
  @Index(name = "index_illegal_plate")
  public String getPlate() {
    return plate;
  }

  public void setPlate(String plate) {
    this.plate = plate;
  }

  public Date getIllegalDate() {
    return illegalDate;
  }

  public void setIllegalDate(Date illegalDate) {
    this.illegalDate = illegalDate;
  }

}
