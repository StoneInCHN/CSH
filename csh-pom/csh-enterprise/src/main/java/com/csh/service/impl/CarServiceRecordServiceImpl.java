package com.csh.service.impl; 

import java.util.List;

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.CarServiceRecord;
import com.csh.dao.CarServiceRecordDao;
import com.csh.service.CarServiceRecordService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("carServiceRecordServiceImpl")
public class CarServiceRecordServiceImpl extends BaseServiceImpl<CarServiceRecord,Long> implements CarServiceRecordService {

      @Resource(name="carServiceRecordDaoImpl")
      private CarServiceRecordDao carServiceRecordDao;
      @Resource
      public void setBaseDao(CarServiceRecordDao carServiceRecordDao) {
         super.setBaseDao(carServiceRecordDao);
  }

      @Override
      public List<CarServiceRecord> findCurrentClearingRecords ()
      {
        return carServiceRecordDao.callProcedureWithResult ("findCurrentClearingServiceRecord", 1,7);
      }
}