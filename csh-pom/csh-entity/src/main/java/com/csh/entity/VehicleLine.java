package com.csh.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;
import org.springframework.web.multipart.MultipartFile;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.csh.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 车系
 * 
 */
@Entity
@Table(name = "csh_vehicle_line")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_vehicle_line_sequence")
public class VehicleLine extends BaseEntity {
  private static final long serialVersionUID = 1L;


  /**
   * 名称首字母
   */
  private String code;


  /**
   * 树形节点级别
   */
  private int grade;

  /**
   * 图片
   */
  private String icon;

  /**
   * 名称
   */
  private String name;

  private VehicleLine parent;

  private Set<VehicleLine> children = new HashSet<VehicleLine>();

  private Set<VehicleBrandDetail> brandDetails = new HashSet<VehicleBrandDetail>();

  private VehicleBrand vehicleBrand;

  private MultipartFile iconFile;

  private Set<ItemPart> itemParts;
  
  @OneToMany(mappedBy = "parent")
  public Set<VehicleLine> getChildren() {
    return children;
  }

  public void setChildren(Set<VehicleLine> children) {
    this.children = children;
  }

  @OneToMany(mappedBy = "vehicleLine")
  public Set<VehicleBrandDetail> getBrandDetails() {
    return brandDetails;
  }

  public void setBrandDetails(Set<VehicleBrandDetail> brandDetails) {
    this.brandDetails = brandDetails;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }


  public int getGrade() {
    return grade;
  }

  public void setGrade(int grade) {
    this.grade = grade;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }


  @JsonProperty
  @Field(index = org.hibernate.search.annotations.Index.YES,store = Store.NO,analyze = Analyze.NO)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }



  @JsonProperty
  @ManyToOne(fetch = FetchType.EAGER)
  @IndexedEmbedded
  public VehicleBrand getVehicleBrand() {
    return vehicleBrand;
  }

  public void setVehicleBrand(VehicleBrand vehicleBrand) {
    this.vehicleBrand = vehicleBrand;
  }

  @ManyToOne
  public VehicleLine getParent() {
    return parent;
  }

  public void setParent(VehicleLine parent) {
    this.parent = parent;
  }

  @Transient
  public MultipartFile getIconFile() {
    return iconFile;
  }

  public void setIconFile(MultipartFile iconFile) {
    this.iconFile = iconFile;
  }

  @ManyToMany(mappedBy = "vehicleLines")
  public Set<ItemPart> getItemParts ()
  {
    return itemParts;
  }

  public void setItemParts (Set<ItemPart> itemParts)
  {
    this.itemParts = itemParts;
  }

}
