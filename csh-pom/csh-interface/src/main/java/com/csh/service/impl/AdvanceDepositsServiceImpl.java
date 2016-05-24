package com.csh.service.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csh.dao.AdvanceDepositsDao;
import com.csh.dao.EndUserDao;
import com.csh.entity.AdvanceDeposits;
import com.csh.entity.EndUser;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.AdvanceDepositsService;

@Service("advanceDepositsServiceImpl")
public class AdvanceDepositsServiceImpl extends BaseServiceImpl<AdvanceDeposits, Long> implements
    AdvanceDepositsService {

  @Resource(name = "advanceDepositsDaoImpl")
  private AdvanceDepositsDao advanceDepositsDao;

  @Resource(name = "endUserDaoImpl")
  private EndUserDao endUserDao;

  @Resource(name = "advanceDepositsDaoImpl")
  public void setBaseDao(AdvanceDepositsDao advanceDepositsDao) {
    super.setBaseDao(advanceDepositsDao);
  }

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void updateAdvanceDeposit(Long userId, BigDecimal amount) {
    EndUser endUser = endUserDao.find(userId);
    AdvanceDeposits advanceDeposits = endUser.getAdvanceDeposits();
    BigDecimal balanceAmount = advanceDeposits.getBalanceAmount().add(amount);
    advanceDeposits.setBalanceAmount(balanceAmount);
    advanceDepositsDao.merge(advanceDeposits);
  }
}
