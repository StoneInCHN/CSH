package com.csh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.WalletRecordDao;
import com.csh.entity.WalletRecord;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.WalletRecordService;

@Service("walletRecordServiceImpl")
public class WalletRecordServiceImpl extends BaseServiceImpl<WalletRecord, Long> implements
    WalletRecordService {

  @Resource(name = "walletRecordDaoImpl")
  private WalletRecordDao walletRecordDao;

  @Resource(name = "walletRecordDaoImpl")
  public void setBaseDao(WalletRecordDao walletRecordDao) {
    super.setBaseDao(walletRecordDao);
  }

  @Override
  public WalletRecord getRecordByOrderNum(String orderNum) {
    return walletRecordDao.getRecordByOrderNum(orderNum);
  }
}
