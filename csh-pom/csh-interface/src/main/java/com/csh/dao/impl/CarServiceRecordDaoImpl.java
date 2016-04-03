package com.csh.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.csh.dao.CarServiceRecordDao;
import com.csh.entity.CarServiceRecord;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;

@Repository("carServiceRecordDaoImpl")
public class CarServiceRecordDaoImpl extends BaseDaoImpl<CarServiceRecord, Long> implements
    CarServiceRecordDao {

  @Override
  public Page<CarServiceRecord> getBuyRecordListByUserAndCategory(Long userId, Long categoryId,
      Pageable pageable) {
    if (userId == null || categoryId == null) {
      return null;
    }
    try {
      String jpql =
          "select record from CarServiceRecord AS record inner join record.carService as service WHERE service.serviceCategory.id = :categoryId and record.endUser.id = :userId order by record.createDate desc";
      Map<String, Object> paramMap = new HashMap<String, Object>();
      paramMap.put("userId", userId);
      paramMap.put("categoryId", categoryId);
      return findPageCustomized(pageable, jpql, paramMap);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

}
