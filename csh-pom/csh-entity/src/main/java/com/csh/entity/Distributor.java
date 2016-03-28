package com.csh.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.csh.entity.base.BaseEntity;

/**
 * 经销商
 * 
 * @author tanbiao
 *
 */
@Indexed(index = "Distributor")
@Entity
@Table(name = "csh_distributor")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_distributor_sequence")
public class Distributor extends BaseEntity {

  private static final long serialVersionUID = -7728373010431015082L;

  /**
   * 名称
   */
  private String distributorName;

  /**
   * 简介
   */
  private String distributorIntro;

  /**
   * 联系电话
   */
  private String distributorPhone;

  /**
   * 地址
   */
  private String distributorAddress;


  /**
   * 用户
   */
  private Admin admin;

  /** 地区 */
  private Area area;



  @Field(store = Store.YES, index = Index.TOKENIZED, analyzer = @Analyzer(impl = IKAnalyzer.class))
  public String getDistributorName() {
    return distributorName;
  }

  public void setDistributorName(String distributorName) {
    this.distributorName = distributorName;
  }

  public String getDistributorIntro() {
    return distributorIntro;
  }

  public void setDistributorIntro(String distributorIntro) {
    this.distributorIntro = distributorIntro;
  }

  public String getDistributorPhone() {
    return distributorPhone;
  }

  public void setDistributorPhone(String distributorPhone) {
    this.distributorPhone = distributorPhone;
  }

  public String getDistributorAddress() {
    return distributorAddress;
  }

  public void setDistributorAddress(String distributorAddress) {
    this.distributorAddress = distributorAddress;
  }


  @OneToOne(fetch = FetchType.LAZY)
  public Admin getAdmin() {
    return admin;
  }

  public void setAdmin(Admin admin) {
    this.admin = admin;
  }

  /**
   * 获取地区
   * 
   * @return 地区
   */
  @ManyToOne(fetch = FetchType.LAZY)
  public Area getArea() {
    return area;
  }

  /**
   * 设置地区
   * 
   * @param area 地区
   */
  public void setArea(Area area) {
    this.area = area;
  }



}
