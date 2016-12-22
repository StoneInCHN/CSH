package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.CarServiceRecordPartInst;
import com.csh.dao.CarServiceRecordPartInstDao;
import com.csh.service.CarServiceRecordPartInstService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("carServiceRecordPartInstServiceImpl")
public class CarServiceRecordPartInstServiceImpl extends BaseServiceImpl<CarServiceRecordPartInst,Long> implements CarServiceRecordPartInstService {

      @Resource(name="carServiceRecordPartInstDaoImpl")
      public void setBaseDao(CarServiceRecordPartInstDao carServiceRecordPartInstDao) {
         super.setBaseDao(carServiceRecordPartInstDao);
  }
}