package com.csh.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.ChargeStatus;
import com.csh.entity.commonenum.CommonEnum.PaymentType;
import com.csh.lucene.DateBridgeImpl;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 汽车服务购买记录
 *
 */

@Entity
@Indexed(index = "carServiceTenantDeductRecord")
@Table(name = "csh_car_service_tenant_deduct_record")
@SequenceGenerator(name = "sequenceGenerator",
    sequenceName = "csh_car_service_tenant_deduct_record_sequence")
public class CarServiceTenantDeductRecord extends BaseEntity {

  /**
     *
     */
  private static final long serialVersionUID = 4496524488163375888L;

  /**
   * 所购买的服务
   */
  private CarService carService;
  /**
   * 购买服务的用户
   */
  private EndUser endUser;

  /**
   * 支付方式
   */
  private PaymentType paymentType;

  private Date paymentDate;
  /**
   * 付款状态
   */
  private ChargeStatus chargeStatus;

  /**
   * 服务单价
   */
  private BigDecimal price;

  /**
   * 商家名称
   */
  private String tenantName;
  /**
   * 商家图片
   */
  private String tenantPhoto;

  /**
   * 结算时间
   */
  private Date clearingDate;


  /**
   * 订单完成时间
   */
  private Date finishDate;

  /**
   * 车牌
   */
  private Vehicle vehicle;

  private TenantDeductClearingRecord tenantDeductClearingRecord;

  /**
   * 租户ID
   */
  private Long tenantID;

  /**
   * 记录编号，自动生成
   */
  private String recordNo;
  /**
   * 提成金额
   */
  private BigDecimal deductMoney;

  public Date getFinishDate() {
    return finishDate;
  }

  public void setFinishDate(Date finishDate) {
    this.finishDate = finishDate;
  }


  @Column(length = 200)
  public String getTenantPhoto() {
    return tenantPhoto;
  }

  public void setTenantPhoto(String tenantPhoto) {
    this.tenantPhoto = tenantPhoto;
  }

  @Column(length = 30)
  @JsonProperty
  public String getRecordNo() {
    return recordNo;
  }

  public void setRecordNo(String recordNo) {
    this.recordNo = recordNo;
  }

  @Index(name = "carServiceTenantDeductRecord_tenantid")
  @Field(store = Store.NO, index = org.hibernate.search.annotations.Index.UN_TOKENIZED)
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  @Column(length = 80)
  @JsonProperty
  public String getTenantName() {
    return tenantName;
  }

  public void setTenantName(String tenantName) {
    this.tenantName = tenantName;
  }

  @ManyToOne(fetch = FetchType.EAGER)
  @JsonProperty
  @IndexedEmbedded
  public CarService getCarService() {
    return carService;
  }

  public void setCarService(CarService carService) {
    this.carService = carService;
  }

  @ManyToOne(fetch = FetchType.EAGER)
  @JsonProperty
  @IndexedEmbedded
  public EndUser getEndUser() {
    return endUser;
  }

  public void setEndUser(EndUser endUser) {
    this.endUser = endUser;
  }

  @JsonProperty
  @Field(store = Store.NO, index = org.hibernate.search.annotations.Index.UN_TOKENIZED)
  public PaymentType getPaymentType() {
    return paymentType;
  }

  public void setPaymentType(PaymentType paymentType) {
    this.paymentType = paymentType;
  }

  @JsonProperty
  @Field(store = Store.NO, index = org.hibernate.search.annotations.Index.UN_TOKENIZED)
  public ChargeStatus getChargeStatus() {
    return chargeStatus;
  }

  public void setChargeStatus(ChargeStatus chargeStatus) {
    this.chargeStatus = chargeStatus;
  }

  // @OneToOne(mappedBy = "carServiceRecord")
  // public MaintainReservation getMaintainReservation() {
  // return maintainReservation;
  // }
  //
  // public void setMaintainReservation(MaintainReservation maintainReservation) {
  // this.maintainReservation = maintainReservation;
  // }
  //
  // @OneToOne(mappedBy = "carServiceRecord")
  // public RepareReservation getRepareReservation() {
  // return repareReservation;
  // }
  //
  // public void setRepareReservation(RepareReservation repareReservation) {
  // this.repareReservation = repareReservation;
  // }


  @Column(scale = 2, precision = 10, nullable = false)
  @JsonProperty
  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  @JsonProperty
  // @Field(index = org.hibernate.search.annotations.Index.UN_TOKENIZED, store = Store.NO)
  // @FieldBridge(impl = DateBridgeImpl.class)
  public Date getClearingDate() {
    return clearingDate;
  }

  public void setClearingDate(Date clearingDate) {
    this.clearingDate = clearingDate;

  }

  @ManyToOne
  public TenantDeductClearingRecord getTenantDeductClearingRecord() {
    return tenantDeductClearingRecord;
  }

  public void setTenantDeductClearingRecord(TenantDeductClearingRecord tenantDeductClearingRecord) {
    this.tenantDeductClearingRecord = tenantDeductClearingRecord;
  }

  @JsonProperty
  @Field(index = org.hibernate.search.annotations.Index.UN_TOKENIZED, store = Store.NO)
  @FieldBridge(impl = DateBridgeImpl.class)
  public Date getPaymentDate() {
    return paymentDate;
  }

  public void setPaymentDate(Date paymentDate) {
    this.paymentDate = paymentDate;
  }

  @ManyToOne
  public Vehicle getVehicle() {
    return vehicle;
  }

  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }

  @JsonProperty
  public BigDecimal getDeductMoney() {
    return deductMoney;
  }

  public void setDeductMoney(BigDecimal deductMoney) {
    this.deductMoney = deductMoney;
  }

}
