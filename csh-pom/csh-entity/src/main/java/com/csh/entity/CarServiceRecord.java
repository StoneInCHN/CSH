package com.csh.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.ChargeStatus;
import com.csh.entity.commonenum.CommonEnum.PaymentType;

/**
 * 汽车服务购买记录
 *
 */

@Entity
@Table (name = "csh_car_service_record")
@SequenceGenerator (name = "sequenceGenerator", sequenceName = "csh_car_service_record_sequence")
public class CarServiceRecord extends BaseEntity
{

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

  @ManyToOne
  public CarService getCarService ()
  {
    return carService;
  }

  public void setCarService (CarService carService)
  {
    this.carService = carService;
  }

  @ManyToOne
  public EndUser getEndUser ()
  {
    return endUser;
  }

  public void setEndUser (EndUser endUser)
  {
    this.endUser = endUser;
  }

  public PaymentType getPaymentType ()
  {
    return paymentType;
  }

  public void setPaymentType (PaymentType paymentType)
  {
    this.paymentType = paymentType;
  }

  public ChargeStatus getChargeStatus ()
  {
    return chargeStatus;
  }

  public void setChargeStatus (ChargeStatus chargeStatus)
  {
    this.chargeStatus = chargeStatus;
  }

  @OneToOne(mappedBy = "carServiceRecord")
  public MaintainReservation getMaintainReservation ()
  {
    return maintainReservation;
  }

  public void setMaintainReservation (MaintainReservation maintainReservation)
  {
    this.maintainReservation = maintainReservation;
  }

  @OneToOne(mappedBy = "carServiceRecord")
  public RepareReservation getRepareReservation ()
  {
    return repareReservation;
  }

  public void setRepareReservation (RepareReservation repareReservation)
  {
    this.repareReservation = repareReservation;
  }

  @Column (scale = 2, precision = 10, nullable = false)
  public BigDecimal getPrice ()
  {
    return price;
  }

  public void setPrice (BigDecimal price)
  {
    this.price = price;
  }

}
