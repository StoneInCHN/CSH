package com.csh.service;

import java.math.BigDecimal;

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
      ChargeStatus chargeStatus, BigDecimal price, PaymentType paymentType);
}
