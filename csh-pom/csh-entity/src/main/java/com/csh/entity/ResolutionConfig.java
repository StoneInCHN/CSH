package com.csh.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.csh.entity.base.BaseEntity;

/**
 * 手机分辨率配置
 *
 */
@Entity
@Table(name = "csh_resolution_config")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_resolution_config_sequence")
public class ResolutionConfig extends BaseEntity {
  
  private static final long serialVersionUID = 1L;
  /**
   * 名称， 例如 Apple iPhone 5s
   */
  private String name;
  /**
   * 宽（像素），例如 640px
   */
  private Integer piWidth; 
  /**
   * 高（像素），例如 1136px
   */
  private Integer piHeight;
  /**
   * 备注
   */
  private String remark;
  
  private Set<Advertisement> ads;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getPiWidth() {
    return piWidth;
  }

  public void setPiWidth(Integer piWidth) {
    this.piWidth = piWidth;
  }

  public Integer getPiHeight() {
    return piHeight;
  }

  public void setPiHeight(Integer piHeight) {
    this.piHeight = piHeight;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
  
  @OneToMany(mappedBy = "resolutionConfig", fetch = FetchType.LAZY)
  public Set<Advertisement> getAds() {
    return ads;
  }

  public void setAds(Set<Advertisement> ads) {
    this.ads = ads;
  }
  
  
}
