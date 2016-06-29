package com.csh.service;

import com.csh.entity.WalletRecord;
import com.csh.framework.service.BaseService;

public interface WalletRecordService extends BaseService<WalletRecord, Long> {

  /**
   * 根据订单号获取记录
   * 
   * @param orderNum
   * @return
   */
  WalletRecord getRecordByOrderNum(String orderNum);
}
