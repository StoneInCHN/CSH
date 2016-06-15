package com.csh.service;

import java.math.BigDecimal;

import com.csh.entity.AccountBalance;
import com.csh.entity.EndUser;
import com.csh.framework.service.BaseService;

public interface AccountBalanceService extends BaseService<AccountBalance, Long> {


  /**
   * 获取用户线下转线上余额
   * 
   * @param endUser
   * @return
   */
  BigDecimal getOfflineBalance(EndUser endUser);

  /**
   * 获取用户线下转线上余额
   * 
   * @param endUser
   * @return
   */
  AccountBalance getOfflineBalanceByTenant(EndUser endUser, Long serviceId);
}
