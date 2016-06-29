package com.csh.dao;

import com.csh.entity.WalletRecord;
import com.csh.framework.dao.BaseDao;

public interface WalletRecordDao extends BaseDao<WalletRecord, Long> {

  /**
   * 根据订单号获取记录
   * 
   * @param orderNum
   * @return
   */
  WalletRecord getRecordByOrderNum(String orderNum);
}
