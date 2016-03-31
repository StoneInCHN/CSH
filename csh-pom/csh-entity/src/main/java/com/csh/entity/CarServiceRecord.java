package com.csh.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.ChargeStatus;
import com.csh.entity.commonenum.CommonEnum.PaymentType;

/**
 * 汽车服务购买记录
 *
 */

@Entity
@Table(name = "csh_car_service_record")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_car_service_record_sequence")
public class CarServiceRecord extends BaseEntity {

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

  /**
   * 付款状态
   */
  private ChargeStatus chargeStatus;

  /**
   * 服务单价
   */
  private BigDecimal price;

  /**
   * 保养预约
   */
  private MaintainReservation maintainReservation;

  /**
   * 维修预约
   */
  private RepareReservation repareReservation;
  /**
   * 商家名称
   */
  private String tenantName;

  /**
   * 结算时间
   */
  private Date balanceDate;


  private TenantClearingRecord tenantClearingRecord;

  private VehicleInsurance vehicleInsurance;
  /**
   * 租户ID
   */
  private Long tenantID;

  /**
   * 记录编号，自动生成
   */
  private String recordNo;


  @Column(length = 30)
  public String getRecordNo() {
    return recordNo;
  }

  public void setRecordNo(String recordNo) {
    this.recordNo = recordNo;
  }

  @Index(name = "carServiceRecord_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  @Column(length = 80)
  public String getTenantName() {
    return tenantName;
  }

  public void setTenantName(String tenantName) {
    this.tenantName = tenantName;
  }

  @ManyToOne
  public CarService getCarService() {
    return carService;
  }

  public void setCarService(CarService carService) {
    this.carService = carService;
  }

  @ManyToOne
  public EndUser getEndUser() {
    return endUser;
  }

  public void setEndUser(EndUser endUser) {
    this.endUser = endUser;
  }

  public PaymentType getPaymentType() {
    return paymentType;
  }

  public void setPaymentType(PaymentType paymentType) {
    this.paymentType = paymentType;
  }

  public ChargeStatus getChargeStatus() {
    return chargeStatus;
  }

  public void setChargeStatus(ChargeStatus chargeStatus) {
    this.chargeStatus = chargeStatus;
  }

  @OneToOne(mappedBy = "carServiceRecord")
  public MaintainReservation getMaintainReservation() {
    return maintainReservation;
  }

  public void setMaintainReservation(MaintainReservation maintainReservation) {
    this.maintainReservation = maintainReservation;
  }

  @OneToOne(mappedBy = "carServiceRecord")
  public RepareReservation getRepareReservation() {
    return repareReservation;
  }

  public void setRepareReservation(RepareReservation repareReservation) {
    this.repareReservation = repareReservation;
  }

  @Column(scale = 2, precision = 10, nullable = false)
  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Date getBalanceDate() {
    return balanceDate;
  }

  public void setBalanceDate(Date balanceDate) {
    this.balanceDate = balanceDate;
  }

  @ManyToOne
  public TenantClearingRecord getTenantClearingRecord() {
    return tenantClearingRecord;
  }

  public void setTenantClearingRecord(TenantClearingRecord tenantClearingRecord) {
    this.tenantClearingRecord = tenantClearingRecord;
  }


  @OneToOne(mappedBy = "carServiceRecord")
  public VehicleInsurance getVehicleInsurance() {
    return vehicleInsurance;
  }

  public void setVehicleInsurance(VehicleInsurance vehicleInsurance) {
    this.vehicleInsurance = vehicleInsurance;
  }


}
