package com.csh.service;

import java.math.BigDecimal;

import com.csh.entity.AdvanceDeposits;
import com.csh.framework.service.BaseService;

public interface AdvanceDepositsService extends BaseService<AdvanceDeposits, Long> {
  /**
   * 充值后购买设备款项
   * 
   * @param userId
   * @param amount
   */
  void updateAdvanceDeposit(Long userId, BigDecimal amount);
}
