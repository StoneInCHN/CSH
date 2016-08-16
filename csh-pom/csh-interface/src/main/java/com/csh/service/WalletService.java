package com.csh.service;

import java.math.BigDecimal;

import com.csh.entity.Wallet;
import com.csh.entity.commonenum.CommonEnum.SystemConfigKey;
import com.csh.framework.service.BaseService;

public interface WalletService extends BaseService<Wallet, Long> {

  /**
   * 充值后更新钱包
   * 
   * @param userId
   * @param amount
   */
  void updateWallet(Long userId, BigDecimal amount, String recordNo);


  /**
   * 赠送基金红包
   * 
   * @param wallet
   * @param systemConfigKey
   * @return
   */
  Wallet giftRedPacket(Wallet wallet, SystemConfigKey systemConfigKey, String remark);

}
