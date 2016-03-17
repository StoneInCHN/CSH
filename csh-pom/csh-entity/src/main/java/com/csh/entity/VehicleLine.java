package com.csh.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.csh.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 车系
 * 
 */
@Entity
@Table (name = "csh_vehicle_line")
@SequenceGenerator (name = "sequenceGenerator", sequenceName = "csh_vehicle_line_sequence")
public class VehicleLine extends BaseEntity
{
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
  
  private Set<VehicleBrandDetail> children = new HashSet<VehicleBrandDetail> ();

  private VehicleBrand vehicleBrand;

  public String getCode ()
  {
    return code;
  }

  public void setCode (String code)
  {
    this.code = code;
  }


  public int getGrade ()
  {
    return grade;
  }

  public void setGrade (int grade)
  {
    this.grade = grade;
  }

  public String getIcon ()
  {
    return icon;
  }

  public void setIcon (String icon)
  {
    this.icon = icon;
  }


  @JsonProperty
  @Field(index=org.hibernate.search.annotations.Index.TOKENIZED,analyzer = @Analyzer(impl = IKAnalyzer.class))
  public String getName ()
  {
    return name;
  }

  public void setName (String name)
  {
    this.name = name;
  }

  
  @OneToMany(mappedBy="vehicleLine")
  public Set<VehicleBrandDetail> getChildren ()
  {
    return children;
  }

  public void setChildren (Set<VehicleBrandDetail> children)
  {
    this.children = children;
  }

  @JsonProperty
  @ManyToOne(fetch=FetchType.EAGER)
  @IndexedEmbedded
  public VehicleBrand getVehicleBrand ()
  {
    return vehicleBrand;
  }

  public void setVehicleBrand (VehicleBrand vehicleBrand)
  {
    this.vehicleBrand = vehicleBrand;
  }

  @ManyToOne
  public VehicleLine getParent ()
  {
    return parent;
  }

  public void setParent (VehicleLine parent)
  {
    this.parent = parent;
  }

}