package com.csh.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import com.csh.entity.base.BaseEntity;

/**
 * 保险公司信息
 * 
 * @author tanbiao
 *
 */
@Entity
@Table(name = "csh_insurance_company")
@Indexed(index = "insuranceCompany")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_insurance_company_sequence")
public class InsuranceCompany extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = 4276670591170096994L;

  /**
   * 公司名字
   */
  private String name;

  private Long parentId;

  /**
   * 公司所在地区
   */
  private Area area;

  /**
   * 详细地址
   */
  private String address;

  /**
   * 省
   */
  private String province;

  /**
   * 市
   */
  private String municipality;

  /**
   * 区 或县
   */
  private String prefecture;

  /**
   * 电话号码
   */
  private String phoneNo;

  @Field(store = Store.NO,index = Index.UN_TOKENIZED)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  public Area getArea() {
    return area;
  }

  public void setArea(Area area) {
    this.area = area;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getMunicipality() {
    return municipality;
  }

  public void setMunicipality(String municipality) {
    this.municipality = municipality;
  }

  public String getPrefecture() {
    return prefecture;
  }

  public void setPrefecture(String prefecture) {
    this.prefecture = prefecture;
  }

  public String getPhoneNo() {
    return phoneNo;
  }

  public void setPhoneNo(String phoneNo) {
    this.phoneNo = phoneNo;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }



}
