package com.csh.entity.estore;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;
import org.hibernate.validator.constraints.NotEmpty;

import com.csh.entity.base.BaseEntity;

/**
 * Entity - 退货项
 * 
 */
@Entity
@Table(name = "csh_returns_item", indexes = {@javax.persistence.Index(
    name = "returnsItem_tenantid", columnList = "tenantID")})
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_returns_item_sequence")
public class ReturnsItem extends BaseEntity {

  private static final long serialVersionUID = -4112374596087084162L;

  /** 商品编号 */
  private String sn;

  /** 商品名称 */
  private String name;

  /** 数量 */
  private Integer quantity;

  /** 退货单 */
  private Returns returns;

  /** 商品缩略图 */
  private String thumbnail;

  /** 商品价格 */
  private BigDecimal price;

  /**
   * 租户ID
   */
  private Long tenantID;

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }


  public String getThumbnail() {
    return thumbnail;
  }

  public void setThumbnail(String thumbnail) {
    this.thumbnail = thumbnail;
  }

  @Field(index = org.hibernate.search.annotations.Index.YES, analyze = Analyze.NO, store = Store.NO)
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  /**
   * 获取商品编号
   * 
   * @return 商品编号
   */
  @NotEmpty
  @Column(nullable = false, updatable = false)
  public String getSn() {
    return sn;
  }

  /**
   * 设置商品编号
   * 
   * @param sn 商品编号
   */
  public void setSn(String sn) {
    this.sn = sn;
  }

  /**
   * 获取商品名称
   * 
   * @return 商品名称
   */
  @NotEmpty
  @Column(nullable = false, updatable = false)
  public String getName() {
    return name;
  }

  /**
   * 设置商品名称
   * 
   * @param name 商品名称
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * 获取数量
   * 
   * @return 数量
   */
  @NotNull
  @Min(1)
  @Column(nullable = false, updatable = false)
  public Integer getQuantity() {
    return quantity;
  }

  /**
   * 设置数量
   * 
   * @param quantity 数量
   */
  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  /**
   * 获取退货单
   * 
   * @return 退货单
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false, updatable = false)
  public Returns getReturns() {
    return returns;
  }

  /**
   * 设置退货单
   * 
   * @param returns 退货单
   */
  public void setReturns(Returns returns) {
    this.returns = returns;
  }

}
