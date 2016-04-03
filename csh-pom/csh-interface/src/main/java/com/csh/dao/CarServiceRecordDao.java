package com.csh.dao;

import com.csh.entity.CarServiceRecord;
import com.csh.framework.dao.BaseDao;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;

public interface CarServiceRecordDao extends BaseDao<CarServiceRecord, Long> {

  public Page<CarServiceRecord> getBuyRecordListByUserAndCategory(Long userId, Long categoryId,
      Pageable pageable);
}
