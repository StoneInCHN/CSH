package com.csh.entity;



import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;

import com.csh.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "csh_service_category")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_service_category_sequence")
public class ServiceCategory extends BaseEntity {

  private static final long serialVersionUID = 238134011029171283L;

  /**
   * 分类名称
   */
  private String categoryName;

  /**
   * 描述
   */
  private String categoryDesc;

  /**
   * 汽车服务
   */
  private Set<CarService> autoServices = new HashSet<CarService>();


  public String getCategoryDesc() {
    return categoryDesc;
  }


  public void setCategoryDesc(String categoryDesc) {
    this.categoryDesc = categoryDesc;
  }

  @OneToMany(mappedBy = "serviceCategory")
  public Set<CarService> getAutoServices() {
    return autoServices;
  }


  public void setAutoServices(Set<CarService> autoServices) {
    this.autoServices = autoServices;
  }

  @JsonProperty
  @Field(store = Store.NO, index = Index.UN_TOKENIZED)
  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }



}
