package com.csh.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.springframework.web.multipart.MultipartFile;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.csh.entity.base.BaseEntity;
import com.csh.lucene.LowCaseBridgeImpl;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 车辆品牌表
 * 
 */
@Indexed(index = "vehicleBrand")
@Entity
@Table(name = "csh_vehicle_brand")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_vehicle_brand_sequence")
public class VehicleBrand extends BaseEntity {
  private static final long serialVersionUID = 1L;


  /**
   * 名称首字母
   */
  private String code;


  /**
   * 图片
   */
  private String icon;


  /**
   * 名称
   */
  private String name;

  private MultipartFile iconFile;


  private Set<VehicleLine> children = new HashSet<VehicleLine>();

  /**
   * 服务项配件
   */
  private Set<ItemPart> itemParts;
  
  @JsonProperty
  @Field(index = org.hibernate.search.annotations.Index.YES,store =Store.NO ,analyze = Analyze.NO)
  @FieldBridge(impl = LowCaseBridgeImpl.class)
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }


  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }


  @JsonProperty
  @Field(index = org.hibernate.search.annotations.Index.YES, store = Store.NO,analyze = Analyze.NO)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @OneToMany(mappedBy = "vehicleBrand")
  public Set<VehicleLine> getChildren() {
    return children;
  }

  public void setChildren(Set<VehicleLine> children) {
    this.children = children;
  }

  @Transient
  public MultipartFile getIconFile() {
    return iconFile;
  }

  public void setIconFile(MultipartFile iconFile) {
    this.iconFile = iconFile;
  }

  @ManyToMany(mappedBy = "vehicleBrands")
  public Set<ItemPart> getItemParts ()
  {
    return itemParts;
  }

  public void setItemParts (Set<ItemPart> itemParts)
  {
    this.itemParts = itemParts;
  }



}
