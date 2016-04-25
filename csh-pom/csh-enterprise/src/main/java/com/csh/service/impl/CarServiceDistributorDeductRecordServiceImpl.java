package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.CarServiceDistributorDeductRecord;
import com.csh.dao.CarServiceDistributorDeductRecordDao;
import com.csh.service.CarServiceDistributorDeductRecordService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("carServiceDistributorDeductRecordServiceImpl")
public class CarServiceDistributorDeductRecordServiceImpl extends BaseServiceImpl<CarServiceDistributorDeductRecord,Long> implements CarServiceDistributorDeductRecordService {

      @Resource(name="carServiceDistributorDeductRecordDaoImpl")
      public void setBaseDao(CarServiceDistributorDeductRecordDao carServiceDistributorDeductRecordDao) {
         super.setBaseDao(carServiceDistributorDeductRecordDao);
  }
}