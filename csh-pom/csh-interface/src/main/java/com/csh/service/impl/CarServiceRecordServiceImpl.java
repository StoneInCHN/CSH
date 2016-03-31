package com.csh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.CarServiceRecordDao;
import com.csh.entity.CarServiceRecord;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.CarServiceRecordService;

@Service("carServiceRecordServiceImpl")
public class CarServiceRecordServiceImpl extends BaseServiceImpl<CarServiceRecord, Long> implements
    CarServiceRecordService {

  @Resource(name = "carServiceRecordDaoImpl")
  private CarServiceRecordDao carServiceRecordDao;

  @Resource(name = "carServiceRecordDaoImpl")
  public void setBaseDao(CarServiceRecordDao carServiceRecordDao) {
    super.setBaseDao(carServiceRecordDao);
  }

  @Override
  public Page<CarServiceRecord> getBuyRecordListByUserAndCategory(Long userId, Long categoryId,
      Pageable pageable) {
    return carServiceRecordDao.getBuyRecordListByUserAndCategory(userId, categoryId, pageable);
  }
}
