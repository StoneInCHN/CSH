package com.csh.entity.estore;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
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

import com.csh.entity.Area;
import com.csh.entity.Coupon;
import com.csh.entity.EndUser;
import com.csh.entity.TenantAccount;
import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.AfterSalesStatus;
import com.csh.entity.commonenum.CommonEnum.OrderStatus;
import com.csh.entity.commonenum.CommonEnum.PaymentStatus;
import com.csh.entity.commonenum.CommonEnum.PaymentType;
import com.csh.entity.commonenum.CommonEnum.ShippingStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entity - 订单
 * 
 */
@Entity
@Table(name = "csh_order", indexes = {@Index(name = "order_tenantid", columnList = "tenantID")})
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_order_sequence")
@Indexed(index = "order")
public class Order extends BaseEntity {

  private static final long serialVersionUID = 8370942500343156156L;

  /** 订单名称分隔符 */
  private static final String NAME_SEPARATOR = " ";


  /** 订单编号 */
  private String sn;

  /** 订单状态 */
  private OrderStatus orderStatus;

  /** 支付状态 */
  private PaymentStatus paymentStatus;

  /** 配送状态 */
  private ShippingStatus shippingStatus;

  /** 退款售后状态 */
  private AfterSalesStatus afterSalesStatus;

  /** 支付手续费 */
  private BigDecimal fee;

  /** 运费 */
  private BigDecimal freight;

  /** 促销折扣 */
  private BigDecimal promotionDiscount;

  /** 优惠券折扣 */
  private BigDecimal couponDiscount;

  /** 调整金额 */
  private BigDecimal offsetAmount;

  /** 已付金额 */
  private BigDecimal amountPaid;

  /** 赠送积分 */
  private Long point;

  /** 收货人 */
  private String consignee;

  /** 地区名称 */
  private String areaName;

  /** 地址 */
  private String address;

  /** 邮编 */
  private String zipCode;

  /** 电话 */
  private String phone;

  /** 是否开据发票 */
  private Boolean isInvoice;

  /** 发票抬头 */
  private String invoiceTitle;

  /** 税金 */
  private BigDecimal tax;

  /** 附言 */
  private String memo;

  /** 促销 */
  private String promotion;

  /** 到期时间 */
  private Date expire;

  /** 锁定到期时间 */
  private Date lockExpire;

  /** 是否已分配库存 */
  private Boolean isAllocatedStock;

  /** 配送方式名称 */
  private String shippingMethodName;

  /** 地区 */
  private Area area;

  /** 支付方式 */
  private PaymentType paymentType;

  /** 配送方式 */
  private ShippingMethod shippingMethod;

  /** 操作员 */
  private TenantAccount operator;

  /** 会员 */
  private EndUser endUser;

  /** 优惠券 */
  private Coupon coupon;

  /** 推荐人 */
  // private String reference;


  /** 订单项 */
  private List<OrderItem> orderItems = new ArrayList<OrderItem>();

  /** 订单日志 */
  private Set<OrderLog> orderLogs = new HashSet<OrderLog>();


  // /** 收款单 */
  // private Set<Payment> payments = new HashSet<Payment>();

  /** 退款单 */
  private Set<Refunds> refunds = new HashSet<Refunds>();

  /** 发货单 */
  private Set<Shipping> shippings = new HashSet<Shipping>();

  /** 退货单 */
  private Set<Returns> returns = new HashSet<Returns>();

  /**
   * 租户ID
   */
  private Long tenantID;


  /**
   * 备注
   */
  private String remark;

  /**
   * 线下余额，不参与结算金额
   */
  private BigDecimal offlineBalance;

  @Column(scale = 6, precision = 14)
  public BigDecimal getOfflineBalance() {
    return offlineBalance;
  }

  public void setOfflineBalance(BigDecimal offlineBalance) {
    this.offlineBalance = offlineBalance;
  }

  @Column(length = 100)
  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  @Field(index = org.hibernate.search.annotations.Index.YES, analyze = Analyze.NO, store = Store.NO)
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  /**
   * 获取订单编号
   * 
   * @return 订单编号
   */
  @Column(nullable = false, updatable = false, unique = true, length = 100)
  @JsonProperty
  @Field(index = org.hibernate.search.annotations.Index.YES, store = Store.NO,
      analyzer = @Analyzer(impl = IKAnalyzer.class))
  public String getSn() {
    return sn;
  }

  /**
   * 设置订单编号
   * 
   * @param sn 订单编号
   */
  public void setSn(String sn) {
    this.sn = sn;
  }

  /**
   * 获取订单状态
   * 
   * @return 订单状态
   */
  @Column(nullable = false)
  @JsonProperty
  @Field(index = org.hibernate.search.annotations.Index.YES, analyze = Analyze.NO, store = Store.NO)
  public OrderStatus getOrderStatus() {
    return orderStatus;
  }

  /**
   * 设置订单状态
   * 
   * @param orderStatus 订单状态
   */
  public void setOrderStatus(OrderStatus orderStatus) {
    this.orderStatus = orderStatus;
  }

  /**
   * 获取支付状态
   * 
   * @return 支付状态
   */
  @Column(nullable = false)
  @JsonProperty
  @Field(index = org.hibernate.search.annotations.Index.YES, analyze = Analyze.NO, store = Store.NO)
  public PaymentStatus getPaymentStatus() {
    return paymentStatus;
  }

  /**
   * 设置支付状态
   * 
   * @param paymentStatus 支付状态
   */
  public void setPaymentStatus(PaymentStatus paymentStatus) {
    this.paymentStatus = paymentStatus;
  }

  /**
   * 获取配送状态
   * 
   * @return 配送状态
   */
  @Column(nullable = false)
  @JsonProperty
  @Field(index = org.hibernate.search.annotations.Index.YES, analyze = Analyze.NO, store = Store.NO)
  public ShippingStatus getShippingStatus() {
    return shippingStatus;
  }

  /**
   * 设置配送状态
   * 
   * @param shippingStatus 配送状态
   */
  public void setShippingStatus(ShippingStatus shippingStatus) {
    this.shippingStatus = shippingStatus;
  }

  /**
   * 获取退款售后状态
   * 
   * @return 退款售后状态
   */
  @JsonProperty
  @Field(index = org.hibernate.search.annotations.Index.YES, analyze = Analyze.NO, store = Store.NO)
  public AfterSalesStatus getAfterSalesStatus() {
    return afterSalesStatus;
  }

  /**
   * 设置退款售后状态
   * 
   * @param afterSalesStatus 退款售后状态
   */
  public void setAfterSalesStatus(AfterSalesStatus afterSalesStatus) {
    this.afterSalesStatus = afterSalesStatus;
  }

  /**
   * 获取支付手续费
   * 
   * @return 支付手续费
   */
  @Column(nullable = false, precision = 21, scale = 6)
  public BigDecimal getFee() {
    return fee;
  }

  /**
   * 设置支付手续费
   * 
   * @param fee 支付手续费
   */
  public void setFee(BigDecimal fee) {
    this.fee = fee;
  }

  /**
   * 获取运费
   * 
   * @return 运费
   */
  @NotNull
  @Min(0)
  @Digits(integer = 12, fraction = 3)
  @Column(nullable = false, precision = 21, scale = 6)
  public BigDecimal getFreight() {
    return freight;
  }

  /**
   * 设置运费
   * 
   * @param freight 运费
   */
  public void setFreight(BigDecimal freight) {
    this.freight = freight;
  }

  /**
   * 获取促销折扣
   * 
   * @return 促销折扣
   */
  @Column(nullable = false, updatable = false, precision = 21, scale = 6)
  public BigDecimal getPromotionDiscount() {
    return promotionDiscount;
  }

  /**
   * 设置促销折扣
   * 
   * @param promotionDiscount 促销折扣
   */
  public void setPromotionDiscount(BigDecimal promotionDiscount) {
    this.promotionDiscount = promotionDiscount;
  }

  /**
   * 获取优惠券折扣
   * 
   * @return 优惠券折扣
   */
  @Column(nullable = false, updatable = false, precision = 21, scale = 6)
  public BigDecimal getCouponDiscount() {
    return couponDiscount;
  }

  /**
   * 设置优惠券折扣
   * 
   * @param couponDiscount 优惠券折扣
   */
  public void setCouponDiscount(BigDecimal couponDiscount) {
    this.couponDiscount = couponDiscount;
  }

  /**
   * 获取调整金额
   * 
   * @return 调整金额
   */
  @NotNull
  @Digits(integer = 12, fraction = 3)
  @Column(nullable = false, precision = 21, scale = 6)
  public BigDecimal getOffsetAmount() {
    return offsetAmount;
  }

  /**
   * 设置调整金额
   * 
   * @param offsetAmount 调整金额
   */
  public void setOffsetAmount(BigDecimal offsetAmount) {
    this.offsetAmount = offsetAmount;
  }

  /**
   * 获取已付金额
   * 
   * @return 已付金额
   */
  @Column(nullable = false, precision = 21, scale = 6)
  @JsonProperty
  public BigDecimal getAmountPaid() {
    return amountPaid;
  }

  /**
   * 设置已付金额
   * 
   * @param amountPaid 已付金额
   */
  public void setAmountPaid(BigDecimal amountPaid) {
    this.amountPaid = amountPaid;
  }

  /**
   * 获取赠送积分
   * 
   * @return 赠送积分
   */
  public Long getPoint() {
    return point;
  }

  /**
   * 设置赠送积分
   * 
   * @param point 赠送积分
   */
  public void setPoint(Long point) {
    this.point = point;
  }

  /**
   * 获取收货人
   * 
   * @return 收货人
   */
  @NotEmpty
  @Length(max = 200)
  @Column(nullable = false)
  @JsonProperty
  public String getConsignee() {
    return consignee;
  }

  /**
   * 设置收货人
   * 
   * @param consignee 收货人
   */
  @JsonProperty
  public void setConsignee(String consignee) {
    this.consignee = consignee;
  }

  /**
   * 获取地区名称
   * 
   * @return 地区名称
   */
  @Column(nullable = false)
  @JsonProperty
  public String getAreaName() {
    return areaName;
  }

  /**
   * 设置地区名称
   * 
   * @param areaName 地区名称
   */
  public void setAreaName(String areaName) {
    this.areaName = areaName;
  }

  /**
   * 获取地址
   * 
   * @return 地址
   */
  @NotEmpty
  @Length(max = 200)
  @Column(nullable = false)
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
  @NotEmpty
  @Length(max = 200)
  @Column(nullable = false)
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
  @NotEmpty
  @Length(max = 200)
  @Column(nullable = false)
  @JsonProperty
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
   * 获取是否开据发票
   * 
   * @return 是否开据发票
   */
  @NotNull
  @Column(nullable = false)
  public Boolean getIsInvoice() {
    return isInvoice;
  }

  /**
   * 设置是否开据发票
   * 
   * @param isInvoice 是否开据发票
   */
  public void setIsInvoice(Boolean isInvoice) {
    this.isInvoice = isInvoice;
  }

  /**
   * 获取发票抬头
   * 
   * @return 发票抬头
   */
  @Length(max = 200)
  public String getInvoiceTitle() {
    return invoiceTitle;
  }

  /**
   * 设置发票抬头
   * 
   * @param invoiceTitle 发票抬头
   */
  public void setInvoiceTitle(String invoiceTitle) {
    this.invoiceTitle = invoiceTitle;
  }

  /**
   * 获取税金
   * 
   * @return 税金
   */
  @Min(0)
  @Digits(integer = 12, fraction = 3)
  @Column(nullable = false, precision = 21, scale = 6)
  public BigDecimal getTax() {
    return tax;
  }

  /**
   * 设置税金
   * 
   * @param tax 税金
   */
  public void setTax(BigDecimal tax) {
    this.tax = tax;
  }

  /**
   * 获取附言
   * 
   * @return 附言
   */
  @Length(max = 200)
  @JsonProperty
  public String getMemo() {
    return memo;
  }

  /**
   * 设置附言
   * 
   * @param memo 附言
   */
  public void setMemo(String memo) {
    this.memo = memo;
  }

  /**
   * 获取促销
   * 
   * @return 促销
   */
  @Column(updatable = false)
  public String getPromotion() {
    return promotion;
  }

  /**
   * 设置促销
   * 
   * @param promotion 促销
   */
  public void setPromotion(String promotion) {
    this.promotion = promotion;
  }

  /**
   * 获取到期时间
   * 
   * @return 到期时间
   */
  @JsonProperty
  public Date getExpire() {
    return expire;
  }

  /**
   * 设置到期时间
   * 
   * @param expire 到期时间
   */
  public void setExpire(Date expire) {
    this.expire = expire;
  }

  /**
   * 获取锁定到期时间
   * 
   * @return 锁定到期时间
   */
  public Date getLockExpire() {
    return lockExpire;
  }

  /**
   * 设置锁定到期时间
   * 
   * @param lockExpire 锁定到期时间
   */
  public void setLockExpire(Date lockExpire) {
    this.lockExpire = lockExpire;
  }

  /**
   * 获取是否已分配库存
   * 
   * @return 是否已分配库存
   */
  @Column(nullable = false)
  public Boolean getIsAllocatedStock() {
    return isAllocatedStock;
  }

  /**
   * 设置是否已分配库存
   * 
   * @param isAllocatedStock 是否已分配库存
   */
  public void setIsAllocatedStock(Boolean isAllocatedStock) {
    this.isAllocatedStock = isAllocatedStock;
  }


  /**
   * 获取配送方式名称
   * 
   * @return 配送方式名称
   */
  @Column(nullable = false)
  public String getShippingMethodName() {
    return shippingMethodName;
  }

  /**
   * 设置配送方式名称
   * 
   * @param shippingMethodName 配送方式名称
   */
  public void setShippingMethodName(String shippingMethodName) {
    this.shippingMethodName = shippingMethodName;
  }

  /**
   * 获取地区
   * 
   * @return 地区
   */
  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  @JsonProperty
  public Area getArea() {
    return area;
  }

  /**
   * 设置地区
   * 
   * @param area 地区
   */
  public void setArea(Area area) {
    this.area = area;
  }

  /**
   * 获取支付方式
   * 
   * @return 支付方式
   */
  @NotNull
  public PaymentType getPaymentType() {
    return paymentType;
  }

  /**
   * 设置支付方式
   * 
   * @param paymentMethod 支付方式
   */
  public void setPaymentType(PaymentType paymentType) {
    this.paymentType = paymentType;
  }

  /**
   * 获取配送方式
   * 
   * @return 配送方式
   */
  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  public ShippingMethod getShippingMethod() {
    return shippingMethod;
  }

  /**
   * 设置配送方式
   * 
   * @param shippingMethod 配送方式
   */
  public void setShippingMethod(ShippingMethod shippingMethod) {
    this.shippingMethod = shippingMethod;
  }

  /**
   * 获取操作员
   * 
   * @return 操作员
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @JsonProperty
  public TenantAccount getOperator() {
    return operator;
  }

  /**
   * 设置操作员
   * 
   * @param operator 操作员
   */
  public void setOperator(TenantAccount operator) {
    this.operator = operator;
  }


  /**
   * 获取会员
   * 
   * @return 会员
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false, updatable = false)
  @JsonProperty
  public EndUser getEndUser() {
    return endUser;
  }

  public void setEndUser(EndUser endUser) {
    this.endUser = endUser;
  }

  /**
   * 获取优惠券
   * 
   * @return 优惠券
   */
  @OneToOne(fetch = FetchType.LAZY)
  public Coupon getCoupon() {
    return coupon;
  }

  /**
   * 设置优惠券
   * 
   * @param coupons 优惠券
   */
  public void setCoupon(Coupon coupon) {
    this.coupon = coupon;
  }



  /**
   * 获取订单项
   * 
   * @return 订单项
   */
  @Valid
  @NotEmpty
  @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL,
      orphanRemoval = true)
  @OrderBy("isGift asc")
  @JsonProperty
  public List<OrderItem> getOrderItems() {
    return orderItems;
  }

  /**
   * 设置订单项
   * 
   * @param orderItems 订单项
   */
  public void setOrderItems(List<OrderItem> orderItems) {
    this.orderItems = orderItems;
  }

  /**
   * 获取订单日志
   * 
   * @return 订单日志
   */
  @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @OrderBy("createDate asc")
  public Set<OrderLog> getOrderLogs() {
    return orderLogs;
  }

  /**
   * 设置订单日志
   * 
   * @param orderLogs 订单日志
   */
  public void setOrderLogs(Set<OrderLog> orderLogs) {
    this.orderLogs = orderLogs;
  }


  /**
   * 获取退款单
   * 
   * @return 退款单
   */
  @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  @OrderBy("createDate asc")
  public Set<Refunds> getRefunds() {
    return refunds;
  }

  /**
   * 设置退款单
   * 
   * @param refunds 退款单
   */
  public void setRefunds(Set<Refunds> refunds) {
    this.refunds = refunds;
  }

  /**
   * 获取发货单
   * 
   * @return 发货单
   */
  @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  @OrderBy("createDate asc")
  public Set<Shipping> getShippings() {
    return shippings;
  }

  /**
   * 设置发货单
   * 
   * @param shippings 发货单
   */
  public void setShippings(Set<Shipping> shippings) {
    this.shippings = shippings;
  }

  /**
   * 获取退货单
   * 
   * @return 退货单
   */
  @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  @OrderBy("createDate asc")
  public Set<Returns> getReturns() {
    return returns;
  }

  /**
   * 设置退货单
   * 
   * @param returns 分销商订单
   */
  public void setReturns(Set<Returns> returns) {
    this.returns = returns;
  }

  /**
   * 获取订单名称
   * 
   * @return 订单名称
   */
  @Transient
  @JsonProperty
  public String getName() {
    StringBuffer name = new StringBuffer();
    if (getOrderItems() != null) {
      for (OrderItem orderItem : getOrderItems()) {
        if (orderItem != null && orderItem.getFullName() != null) {
          name.append(NAME_SEPARATOR).append(orderItem.getFullName());
        }
      }
      if (name.length() > 0) {
        name.deleteCharAt(0);
      }
    }
    return name.toString();
  }

  /**
   * 获取商品重量
   * 
   * @return 商品重量
   */
  @Transient
  @JsonProperty
  public int getWeight() {
    int weight = 0;
    if (getOrderItems() != null) {
      for (OrderItem orderItem : getOrderItems()) {
        if (orderItem != null) {
          weight += orderItem.getTotalWeight();
        }
      }
    }
    return weight;
  }

  /**
   * 获取商品数量
   * 
   * @return 商品数量
   */
  @Transient
  @JsonProperty
  public int getQuantity() {
    int quantity = 0;
    if (getOrderItems() != null) {
      for (OrderItem orderItem : getOrderItems()) {
        if (orderItem != null && orderItem.getQuantity() != null) {
          quantity += orderItem.getQuantity();
        }
      }
    }
    return quantity;
  }

  /**
   * 获取已发货数量
   * 
   * @return 已发货数量
   */
  @Transient
  public int getShippedQuantity() {
    int shippedQuantity = 0;
    if (getOrderItems() != null) {
      for (OrderItem orderItem : getOrderItems()) {
        if (orderItem != null && orderItem.getShippedQuantity() != null) {
          shippedQuantity += orderItem.getShippedQuantity();
        }
      }
    }
    return shippedQuantity;
  }

  /**
   * 获取已退货数量
   * 
   * @return 已退货数量
   */
  @Transient
  public int getReturnQuantity() {
    int returnQuantity = 0;
    if (getOrderItems() != null) {
      for (OrderItem orderItem : getOrderItems()) {
        if (orderItem != null && orderItem.getReturnQuantity() != null) {
          returnQuantity += orderItem.getReturnQuantity();
        }
      }
    }
    return returnQuantity;
  }

  /**
   * 获取商品价格
   * 
   * @return 商品价格
   */
  @Transient
  @JsonProperty
  public BigDecimal getPrice() {
    BigDecimal price = new BigDecimal(0);
    if (getOrderItems() != null) {
      for (OrderItem orderItem : getOrderItems()) {
        if (orderItem != null && orderItem.getSubtotal() != null) {
          price = price.add(orderItem.getSubtotal());
        }
      }
    }
    return price;
  }

  /**
   * 获取订单金额
   * 
   * @return 订单金额
   */
  @Transient
  @JsonProperty
  public BigDecimal getAmount() {
    BigDecimal amount = getPrice();
    if (getFee() != null) {
      amount = amount.add(getFee());
    }
    if (getFreight() != null) {
      amount = amount.add(getFreight());
    }
    if (getPromotionDiscount() != null) {
      amount = amount.subtract(getPromotionDiscount());
    }
    if (getCouponDiscount() != null) {
      amount = amount.subtract(getCouponDiscount());
    }
    if (getOffsetAmount() != null) {
      amount = amount.add(getOffsetAmount());
    }
    if (getTax() != null) {
      amount = amount.add(getTax());
    }
    return amount.compareTo(new BigDecimal(0)) > 0 ? amount : new BigDecimal(0);
  }

  /**
   * 获取应付金额
   * 
   * @return 应付金额
   */
  @Transient
  public BigDecimal getAmountPayable() {
    BigDecimal amountPayable = getAmount().subtract(getAmountPaid());
    return amountPayable.compareTo(new BigDecimal(0)) > 0 ? amountPayable : new BigDecimal(0);
  }

  /**
   * 是否已过期
   * 
   * @return 是否已过期
   */
  @Transient
  public boolean isExpired() {
    return getExpire() != null && new Date().after(getExpire());
  }

  /**
   * 获取订单项
   * 
   * @param sn 商品编号
   * @return 订单项
   */
  @Transient
  public OrderItem getOrderItem(String sn) {
    if (sn != null && getOrderItems() != null) {
      for (OrderItem orderItem : getOrderItems()) {
        if (orderItem != null && sn.equalsIgnoreCase(orderItem.getSn())) {
          return orderItem;
        }
      }
    }
    return null;
  }

  /**
   * 判断是否已锁定
   * 
   * @param operator 操作员
   * @return 是否已锁定
   */
  @Transient
  public boolean isLocked(TenantAccount operator) {
    return getLockExpire() != null
        && new Date().before(getLockExpire())
        && ((operator != null && !operator.equals(getOperator())) || (operator == null && getOperator() != null));
  }


  /**
   * 持久化前处理
   */
  @PrePersist
  public void prePersist() {
    if (getArea() != null) {
      setAreaName(getArea().getFullName());
    }
    if (getShippingMethod() != null) {
      setShippingMethodName(getShippingMethod().getName());
    }
  }

  /**
   * 更新前处理
   */
  @PreUpdate
  public void preUpdate() {
    if (getArea() != null) {
      setAreaName(getArea().getFullName());
    }
    if (getShippingMethod() != null) {
      setShippingMethodName(getShippingMethod().getName());
    }
  }

  /**
   * 删除前处理
   */
  @PreRemove
  public void preRemove() {

  }



}
