package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.WalletRecord;
import com.csh.dao.WalletRecordDao;
import com.csh.service.WalletRecordService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("walletRecordServiceImpl")
public class WalletRecordServiceImpl extends BaseServiceImpl<WalletRecord,Long> implements WalletRecordService {

      @Resource(name="walletRecordDaoImpl")
      public void setBaseDao(WalletRecordDao walletRecordDao) {
         super.setBaseDao(walletRecordDao);
  }
}