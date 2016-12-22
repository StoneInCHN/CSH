package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.CarServiceTenantDeductRecord;
import com.csh.dao.CarServiceTenantDeductRecordDao;
import com.csh.service.CarServiceTenantDeductRecordService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("carServiceTenantDeductRecordServiceImpl")
public class CarServiceTenantDeductRecordServiceImpl extends BaseServiceImpl<CarServiceTenantDeductRecord,Long> implements CarServiceTenantDeductRecordService {

      @Resource(name="carServiceTenantDeductRecordDaoImpl")
      public void setBaseDao(CarServiceTenantDeductRecordDao carServiceTenantDeductRecordDao) {
         super.setBaseDao(carServiceTenantDeductRecordDao);
  }
}