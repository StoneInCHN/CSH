package com.csh.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Entity - 地区
 * 
 */
@Entity
@Table(name = "csh_area")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_area_sequence")
public class Area extends OrderEntity {

  private static final long serialVersionUID = -2158109459123036967L;

  /** 树路径分隔符 */
  private static final String TREE_PATH_SEPARATOR = ",";

  /** 名称 */
  private String name;

  /** 全称 */
  private String fullName;

  /** 树路径 */
  private String treePath;

  /** 上级地区 */
  private Area parent;

  /** 下级地区 */
  private Set<Area> children = new HashSet<Area>();

  /** 商家 */
 // private Set<Distributor> vendors = new HashSet<Distributor>();


  /**
   * 获取名称
   * 
   * @return 名称
   */
  @NotEmpty
  @Length(max = 100)
  @Column(nullable = false, length = 100)
  public String getName() {
    return name;
  }

  /**
   * 设置名称
   * 
   * @param name 名称
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * 获取全称
   * 
   * @return 全称
   */
  @Column(nullable = false, length = 500)
  public String getFullName() {
    return fullName;
  }

  /**
   * 设置全称
   * 
   * @param fullName 全称
   */
  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  /**
   * 获取树路径
   * 
   * @return 树路径
   */
  @Column(nullable = false, updatable = false)
  public String getTreePath() {
    return treePath;
  }

  /**
   * 设置树路径
   * 
   * @param treePath 树路径
   */
  public void setTreePath(String treePath) {
    this.treePath = treePath;
  }

  /**
   * 获取上级地区
   * 
   * @return 上级地区
   */
  @ManyToOne(fetch = FetchType.LAZY)
  public Area getParent() {
    return parent;
  }

  /**
   * 设置上级地区
   * 
   * @param parent 上级地区
   */
  public void setParent(Area parent) {
    this.parent = parent;
  }

  /**
   * 获取下级地区
   * 
   * @return 下级地区
   */
  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  @OrderBy("order asc")
  public Set<Area> getChildren() {
    return children;
  }

  /**
   * 设置下级地区
   * 
   * @param children 下级地区
   */
  public void setChildren(Set<Area> children) {
    this.children = children;
  }

/*  @OneToMany(mappedBy = "area", fetch = FetchType.LAZY)
  public Set<Distributor> getVendors() {
    return vendors;
  }

  public void setVendors(Set<Distributor> vendors) {
    this.vendors = vendors;
  }*/

  /**
   * 持久化前处理
   */
  @PrePersist
  public void prePersist() {
    Area parent = getParent();
    if (parent != null) {
      setFullName(parent.getFullName() + getName());
      setTreePath(parent.getTreePath() + parent.getId() + TREE_PATH_SEPARATOR);
    } else {
      setFullName(getName());
      setTreePath(TREE_PATH_SEPARATOR);
    }
  }

  /**
   * 更新前处理
   */
  @PreUpdate
  public void preUpdate() {
    Area parent = getParent();
    if (parent != null) {
      setFullName(parent.getFullName() + getName());
    } else {
      setFullName(getName());
    }
  }


  /**
   * 重写toString方法
   * 
   * @return 全称
   */
  @Override
  public String toString() {
    return getFullName();
  }

}
