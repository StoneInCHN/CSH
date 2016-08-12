package com.csh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.AccountBalanceDao;
import com.csh.entity.AccountBalance;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.AccountBalanceService;

@Service("accountBalanceServiceImpl")
public class AccountBalanceServiceImpl extends BaseServiceImpl<AccountBalance, Long> implements
    AccountBalanceService {

  @Resource(name = "accountBalanceDaoImpl")
  public void setBaseDao(AccountBalanceDao accountBalanceDao) {
    super.setBaseDao(accountBalanceDao);
  }

}
