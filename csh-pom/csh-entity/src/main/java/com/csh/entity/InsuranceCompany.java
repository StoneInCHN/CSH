package com.csh.entity;

import com.csh.entity.base.BaseEntity;

/**
 * 保险公司信息
 * @author tanbiao
 *
 */
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

  private Area area;

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

}
