package com.csh.entity.estore;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.ReturnsStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entity - 退货单
 * 
 */
@Entity
@Table(name = "csh_returns", indexes = {@javax.persistence.Index(name = "returns_tenantid",
    columnList = "tenantID")})
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_returns_sequence")
@Indexed(index = "returns")
public class Returns extends BaseEntity {

  private static final long serialVersionUID = -8019074120457087212L;

  /** 退货单编号 */
  private String sn;

  /** 配送方式 */
  private String shippingMethod;

  /** 物流公司 */
  private String deliveryCorp;

  /** 运单号 */
  private String trackingNo;

  /** 物流费用 */
  private BigDecimal freight;

  /** 发货人 */
  private String shipper;

  /** 地区 */
  private String area;

  /** 地址 */
  private String address;

  /** 邮编 */
  private String zipCode;

  /** 电话 */
  private String phone;

  /** 操作员 */
  private String operator;

  /** 备注 */
  private String memo;

  /** 退货单状态 */
  private ReturnsStatus returnsStatus;

  /** 申请退款金额（计算所得） */
  private BigDecimal returnAmount;

  /** 订单 */
  private Order order;

  /** 退货项 */
  private List<ReturnsItem> returnsItems = new ArrayList<ReturnsItem>();
  /**
   * 租户ID
   */
  private Long tenantID;

  @Field(index = org.hibernate.search.annotations.Index.YES, analyze = Analyze.NO, store = Store.NO)
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  /**
   * 获取编号
   * 
   * @return 编号
   */
  @Column(nullable = false, updatable = false, unique = true, length = 100)
  @JsonProperty
  @Field(index = org.hibernate.search.annotations.Index.YES, store = Store.NO,
      analyzer = @Analyzer(impl = IKAnalyzer.class))
  public String getSn() {
    return sn;
  }

  /**
   * 设置编号
   * 
   * @param sn 编号
   */
  public void setSn(String sn) {
    this.sn = sn;
  }

  /**
   * 获取配送方式
   * 
   * @return 配送方式
   */
  @Column(length = 100)
  @JsonProperty
  public String getShippingMethod() {
    return shippingMethod;
  }

  /**
   * 设置配送方式
   * 
   * @param shippingMethod 配送方式
   */
  public void setShippingMethod(String shippingMethod) {
    this.shippingMethod = shippingMethod;
  }

  /**
   * 获取物流公司
   * 
   * @return 物流公司
   */
  @Column(length = 100)
  @JsonProperty
  public String getDeliveryCorp() {
    return deliveryCorp;
  }

  /**
   * 设置物流公司
   * 
   * @param deliveryCorp 物流公司
   */
  public void setDeliveryCorp(String deliveryCorp) {
    this.deliveryCorp = deliveryCorp;
  }

  /**
   * 获取运单号
   * 
   * @return 运单号
   */
  @Length(max = 200)
  @JsonProperty
  @Field(index = org.hibernate.search.annotations.Index.YES, store = Store.NO,
  analyzer = @Analyzer(impl = IKAnalyzer.class))
  public String getTrackingNo() {
    return trackingNo;
  }

  /**
   * 设置运单号
   * 
   * @param trackingNo 运单号
   */
  public void setTrackingNo(String trackingNo) {
    this.trackingNo = trackingNo;
  }

  /**
   * 获取物流费用
   * 
   * @return 物流费用
   */
  @Min(0)
  @Digits(integer = 12, fraction = 3)
  @Column(precision = 21, scale = 6)
  public BigDecimal getFreight() {
    return freight;
  }

  /**
   * 设置物流费用
   * 
   * @param freight 物流费用
   */
  public void setFreight(BigDecimal freight) {
    this.freight = freight;
  }

  /**
   * 获取发货人
   * 
   * @return 发货人
   */
  @Length(max = 200)
  @JsonProperty
  public String getShipper() {
    return shipper;
  }

  /**
   * 设置发货人
   * 
   * @param shipper 发货人
   */
  public void setShipper(String shipper) {
    this.shipper = shipper;
  }

  /**
   * 获取地区
   * 
   * @return 地区
   */
  @Column(length = 100)
  @JsonProperty
  public String getArea() {
    return area;
  }

  /**
   * 设置地区
   * 
   * @param area 地区
   */
  public void setArea(String area) {
    this.area = area;
  }

  /**
   * 获取地址
   * 
   * @return 地址
   */
  @Length(max = 200)
  @JsonProperty
  public String getAddress() {
    return address;
  }

  /**
   * 设置地址
   * 
   * @param address 地址
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * 获取邮编
   * 
   * @return 邮编
   */
  @Length(max = 200)
  public String getZipCode() {
    return zipCode;
  }

  /**
   * 设置邮编
   * 
   * @param zipCode 邮编
   */
  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  /**
   * 获取电话
   * 
   * @return 电话
   */
  @Length(max = 200)
  public String getPhone() {
    return phone;
  }

  /**
   * 设置电话
   * 
   * @param phone 电话
   */
  public void setPhone(String phone) {
    this.phone = phone;
  }

  /**
   * 获取操作员
   * 
   * @return 操作员
   */
  @Column(length = 100)
  public String getOperator() {
    return operator;
  }

  /**
   * 设置操作员
   * 
   * @param operator 操作员
   */
  public void setOperator(String operator) {
    this.operator = operator;
  }

  /**
   * 获取备注
   * 
   * @return 备注
   */
  @Length(max = 200)
  public String getMemo() {
    return memo;
  }

  /**
   * 设置备注
   * 
   * @param memo 备注
   */
  public void setMemo(String memo) {
    this.memo = memo;
  }

  /**
   * 获取退货单状态
   * 
   * @return
   */
  @JsonProperty
  @Field(index = org.hibernate.search.annotations.Index.YES, analyze = Analyze.NO, store = Store.NO)
  public ReturnsStatus getReturnsStatus() {
    return returnsStatus;
  }

  /**
   * 设置退货单状态
   * 
   * @param returnsStatus
   */
  public void setReturnsStatus(ReturnsStatus returnsStatus) {
    this.returnsStatus = returnsStatus;
  }

  /**
   * 获取 申请退款金额
   * 
   * @return
   */
  @JsonProperty
  public BigDecimal getReturnAmount() {
    return returnAmount;
  }

  /**
   * 设置 申请退款金额
   * 
   * @param returnAmount
   */
  public void setReturnAmount(BigDecimal returnAmount) {
    this.returnAmount = returnAmount;
  }

  /**
   * 获取订单
   * 
   * @return 订单
   */
  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "orders", nullable = false, updatable = false)
  @JsonProperty
  public Order getOrder() {
    return order;
  }

  /**
   * 设置订单
   * 
   * @param order 订单
   */
  public void setOrder(Order order) {
    this.order = order;
  }

  /**
   * 获取退货项
   * 
   * @return 退货项
   */
  @Valid
  @NotEmpty
  @OneToMany(mappedBy = "returns", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  public List<ReturnsItem> getReturnsItems() {
    return returnsItems;
  }

  /**
   * 设置退货项
   * 
   * @param returnsItems 退货项
   */
  public void setReturnsItems(List<ReturnsItem> returnsItems) {
    this.returnsItems = returnsItems;
  }

  /**
   * 获取数量
   * 
   * @return 数量
   */
  @Transient
  public int getQuantity() {
    int quantity = 0;
    if (getReturnsItems() != null) {
      for (ReturnsItem returnsItem : getReturnsItems()) {
        if (returnsItem != null && returnsItem.getQuantity() != null) {
          quantity += returnsItem.getQuantity();
        }
      }
    }
    return quantity;
  }

}
