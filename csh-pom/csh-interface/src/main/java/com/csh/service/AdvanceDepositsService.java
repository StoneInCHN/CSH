package com.csh.service;

import java.math.BigDecimal;

import com.csh.entity.AdvanceDeposits;
import com.csh.entity.commonenum.CommonEnum.PaymentType;
import com.csh.framework.service.BaseService;

public interface AdvanceDepositsService extends BaseService<AdvanceDeposits, Long> {


  /**
   * 充值后购买设备款项
   * 
   * @param userId
   * @param amount
   * @param deviceNo
   * @param paymentType
   */
  void updateAdvanceDeposit(Long userId, BigDecimal amount, String deviceNo,
      PaymentType paymentType, String recordNo);

  /**
   * 是否已经购买该租户下的设备
   * 
   * @param tenantId
   * @param userId
   * @return
   */
  Boolean isAlreadyPurDevice(Long tenantId, Long userId);
}
