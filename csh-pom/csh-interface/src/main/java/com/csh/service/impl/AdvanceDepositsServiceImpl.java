package com.csh.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csh.beans.Message;
import com.csh.dao.AdvanceDepositsDao;
import com.csh.dao.DeviceInfoDao;
import com.csh.dao.EndUserDao;
import com.csh.dao.WalletDao;
import com.csh.entity.AdvanceDeposits;
import com.csh.entity.DeviceInfo;
import com.csh.entity.EndUser;
import com.csh.entity.Wallet;
import com.csh.entity.WalletRecord;
import com.csh.entity.commonenum.CommonEnum.AdvanceUsageType;
import com.csh.entity.commonenum.CommonEnum.BalanceType;
import com.csh.entity.commonenum.CommonEnum.DeviceStatus;
import com.csh.entity.commonenum.CommonEnum.PaymentType;
import com.csh.entity.commonenum.CommonEnum.WalletType;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.AdvanceDepositsService;

@Service("advanceDepositsServiceImpl")
public class AdvanceDepositsServiceImpl extends BaseServiceImpl<AdvanceDeposits, Long> implements
    AdvanceDepositsService {

  @Resource(name = "advanceDepositsDaoImpl")
  private AdvanceDepositsDao advanceDepositsDao;

  @Resource(name = "endUserDaoImpl")
  private EndUserDao endUserDao;

  @Resource(name = "walletDaoImpl")
  private WalletDao walletDao;

  @Resource(name = "deviceInfoDaoImpl")
  private DeviceInfoDao deviceInfoDao;

  @Resource(name = "advanceDepositsDaoImpl")
  public void setBaseDao(AdvanceDepositsDao advanceDepositsDao) {
    super.setBaseDao(advanceDepositsDao);
  }

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void updateAdvanceDeposit(Long userId, BigDecimal amount, String deviceNo,
      PaymentType paymentType, String recordNo) {
    EndUser endUser = endUserDao.find(userId);
    DeviceInfo deviceInfo = deviceInfoDao.getDeviceByDeviceNo(deviceNo);
    AdvanceDeposits advanceDeposits = new AdvanceDeposits();
    advanceDeposits.setDeviceNo(deviceNo);
    advanceDeposits.setEndUser(endUser);
    advanceDeposits.setAmount(amount);
    advanceDeposits.setTenantID(deviceInfo.getTenantID());
    advanceDeposits.setRecordNo(recordNo);
    advanceDeposits.setPaymentType(paymentType);
    advanceDeposits.setUsageType(AdvanceUsageType.DEVICE);
    advanceDeposits.setIsBind(false);
    advanceDepositsDao.persist(advanceDeposits);

    if (PaymentType.WALLET.equals(paymentType)) {
      Wallet wallet = endUser.getWallet();
      wallet.setBalanceAmount(wallet.getBalanceAmount().subtract(amount));
      WalletRecord walletRecord = new WalletRecord();
      walletRecord.setBalanceType(BalanceType.OUTCOME);
      walletRecord.setWalletType(WalletType.MONEY);
      walletRecord.setMoney(amount);
      walletRecord.setRecordNo(recordNo);
      walletRecord.setRemark(Message.success("csh.wallet.chargeIn.purDevice").getContent());
      walletRecord.setWallet(wallet);
      wallet.getWalletRecords().add(walletRecord);
      walletDao.merge(wallet);
    }


    deviceInfo.setDeviceStatus(DeviceStatus.SALEOUT);
    deviceInfoDao.merge(deviceInfo);
  }

  @Override
  public Boolean isAlreadyPurDevice(Long tenantId, Long userId) {
    List<Filter> filters = new ArrayList<Filter>();
    Filter tenantFilter = new Filter("tenantID", Operator.eq, tenantId);
    EndUser endUser = endUserDao.find(userId);
    Filter endUserFilter = new Filter("endUser", Operator.eq, endUser);
    Filter typeFilter = new Filter("usageType", Operator.eq, AdvanceUsageType.DEVICE);
    Filter bindFilter = new Filter("isBind", Operator.eq, false);
    filters.add(bindFilter);
    filters.add(typeFilter);
    filters.add(tenantFilter);
    filters.add(endUserFilter);
    List<AdvanceDeposits> advanceDeposits = advanceDepositsDao.findList(null, null, filters, null);
    if (advanceDeposits != null && advanceDeposits.size() > 0) {
      return true;
    }
    return false;
  }
}
