package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.Wallet;
import com.csh.dao.WalletDao;
import com.csh.service.WalletService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("walletServiceImpl")
public class WalletServiceImpl extends BaseServiceImpl<Wallet,Long> implements WalletService {

      @Resource(name="walletDaoImpl")
      public void setBaseDao(WalletDao walletDao) {
         super.setBaseDao(walletDao);
  }
}