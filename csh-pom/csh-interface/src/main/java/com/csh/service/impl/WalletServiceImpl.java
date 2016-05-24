package com.csh.service.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csh.beans.Message;
import com.csh.dao.AdvanceDepositsDao;
import com.csh.dao.EndUserDao;
import com.csh.dao.WalletDao;
import com.csh.entity.EndUser;
import com.csh.entity.Wallet;
import com.csh.entity.WalletRecord;
import com.csh.entity.commonenum.CommonEnum.BalanceType;
import com.csh.entity.commonenum.CommonEnum.WalletType;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.WalletService;

@Service("walletServiceImpl")
public class WalletServiceImpl extends BaseServiceImpl<Wallet, Long> implements WalletService {

  @Resource(name = "walletDaoImpl")
  private WalletDao walletDao;

  @Resource(name = "endUserDaoImpl")
  private EndUserDao endUserDao;

  @Resource(name = "advanceDepositsDaoImpl")
  private AdvanceDepositsDao advanceDepositsDao;


  @Resource(name = "walletDaoImpl")
  public void setBaseDao(WalletDao walletDao) {
    super.setBaseDao(walletDao);
  }

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void updateWallet(Long userId, BigDecimal amount, String recordNo) {
    EndUser endUser = endUserDao.find(userId);
    Wallet wallet = endUser.getWallet();
    wallet.setBalanceAmount(wallet.getBalanceAmount().add(amount));
    WalletRecord walletRecord = new WalletRecord();
    walletRecord.setBalanceType(BalanceType.INCOME);
    walletRecord.setWalletType(WalletType.MONEY);
    walletRecord.setMoney(amount);
    walletRecord.setRecordNo(recordNo);
    walletRecord.setRemark(Message.success("csh.wallet.chargeIn.record").getContent());
    walletRecord.setWallet(wallet);
    wallet.getWalletRecords().add(walletRecord);
    walletDao.merge(wallet);
  }

}
