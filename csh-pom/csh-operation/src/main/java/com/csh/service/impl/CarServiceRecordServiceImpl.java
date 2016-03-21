package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.CarServiceRecord;
import com.csh.dao.CarServiceRecordDao;
import com.csh.service.CarServiceRecordService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("carServiceRecordServiceImpl")
public class CarServiceRecordServiceImpl extends BaseServiceImpl<CarServiceRecord,Long> implements CarServiceRecordService {

      @Resource(name="carServiceRecordDaoImpl")
      public void setBaseDao(CarServiceRecordDao carServiceRecordDao) {
         super.setBaseDao(carServiceRecordDao);
  }
}