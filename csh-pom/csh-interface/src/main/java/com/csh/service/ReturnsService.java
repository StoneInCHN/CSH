package com.csh.service;

import com.csh.entity.estore.Returns;
import com.csh.framework.service.BaseService;

public interface ReturnsService extends BaseService<Returns, Long> {


  /**
   * 创建/更新退货单
   * 
   * @param orderId
   * @param orderItemIds
   * @param returnsId
   * @param userId
   * @param trackingNo
   * @param deliveryCorp
   * @return
   */
  public Returns createOrEdit(Long orderId, Long[] orderItemIds, Long returnsId, Long userId,
      String trackingNo, String deliveryCorp);
}
