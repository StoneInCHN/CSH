package com.csh.service;

import com.csh.entity.CarServiceRecord;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.framework.service.BaseService;

public interface CarServiceRecordService extends BaseService<CarServiceRecord, Long> {

  /**
   * 根据用户和服务类别获取购买的服务列表
   * 
   * @param userId
   * @return
   */
  public Page<CarServiceRecord> getBuyRecordListByUserAndCategory(Long userId, Long categoryId,
      Pageable pageable);
}
