package com.csh.service;

import java.math.BigDecimal;

import com.csh.entity.Wallet;
import com.csh.framework.service.BaseService;

public interface WalletService extends BaseService<Wallet, Long> {

  /**
   * 充值后更新钱包
   * 
   * @param userId
   * @param amount
   */
  void updateWallet(Long userId, BigDecimal amount, String recordNo);

}
