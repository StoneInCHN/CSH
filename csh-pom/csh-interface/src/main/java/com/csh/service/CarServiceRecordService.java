package com.csh.service;

import java.math.BigDecimal;
import java.sql.Date;

import com.csh.entity.CarService;
import com.csh.entity.CarServiceRecord;
import com.csh.entity.EndUser;
import com.csh.entity.commonenum.CommonEnum.ChargeStatus;
import com.csh.entity.commonenum.CommonEnum.PaymentType;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.framework.service.BaseService;

public interface CarServiceRecordService extends BaseService<CarServiceRecord, Long> {

  /**
   * 根据用户和服务类别获取购买的服务列表
   * 
   * @param userId
   * @return
   */
  public Page<CarServiceRecord> getBuyRecordListByUserAndCategory(Long userId, Long categoryId,
      Pageable pageable);


  /**
   * 用户预约或购买服务是创建购买记录
   * 
   * @param endUser
   * @param carService
   * @param chargeStatus
   * @param price
   * @param paymentType
   */
  public CarServiceRecord createServiceRecord(EndUser endUser, CarService carService,
      ChargeStatus chargeStatus, BigDecimal price, PaymentType paymentType, Date subscribeDate);


  /**
   * 用户购买保险记录
   * 
   * @param endUser
   * @param carService
   * @param price
   * @param company
   * @param IDphoto
   * @param drivingLicensePhoto
   * @param driverLicensePhoto
   * @param isOwned
   * @param isLoaned
   * @return
   */
  public CarServiceRecord createInsuranceRecord(EndUser endUser, CarService carService,
      BigDecimal price, String company, String IDphoto, String drivingLicensePhoto,
      String driverLicensePhoto, Boolean isOwned, Boolean isLoaned);

  /**
   * 根据用户查询是否存在正处于某种状态的消费记录
   * 
   * @param user
   * @param carService
   * @param chargeStatus
   * @return
   */
  public Boolean existRecordByUser(EndUser user, CarService carService, ChargeStatus chargeStatus);

  /**
   * 支付后更新记录状态并计算积分
   * 
   * @param carServiceRecord
   * @return
   */
  public CarServiceRecord updatePayStatus(CarServiceRecord carServiceRecord);
}
