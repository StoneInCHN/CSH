package com.csh.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import com.csh.entity.base.BaseEntity;
import com.csh.lucene.LowCaseBridgeImpl;

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

  /** 树路径分隔符 */
  public static final String TREE_PATH_SEPARATOR = ",";
  
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

  /** 树路径 */
  private String treePath;

  /** 层级 */
  private Integer grade;

  /** 上级服务 */
  private InsuranceCompany parent;

  /** 下级服务 */
  private Set<InsuranceCompany> children = new HashSet<InsuranceCompany>();


  @Field(store = Store.NO, index = Index.UN_TOKENIZED)
  @FieldBridge(impl = LowCaseBridgeImpl.class)
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

  @Column(nullable = false, updatable = false)
  public String getTreePath() {
    return treePath;
  }

  public void setTreePath(String treePath) {
    this.treePath = treePath;
  }

  @Column(nullable = false)
  public Integer getGrade() {
    return grade;
  }

  public void setGrade(Integer grade) {
    this.grade = grade;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  public InsuranceCompany getParent() {
    return parent;
  }

  public void setParent(InsuranceCompany parent) {
    this.parent = parent;
  }

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  public Set<InsuranceCompany> getChildren() {
    return children;
  }

  public void setChildren(Set<InsuranceCompany> children) {
    this.children = children;
  }

  /**
   * 获取树路径
   * 
   * @return 树路径
   */
  @Transient
  public List<Long> getTreePaths() {
    List<Long> treePaths = new ArrayList<Long>();
    String[] ids = StringUtils.split(getTreePath(), TREE_PATH_SEPARATOR);
    if (ids != null) {
      for (String id : ids) {
        treePaths.add(Long.valueOf(id));
      }
    }
    return treePaths;
  }

}
