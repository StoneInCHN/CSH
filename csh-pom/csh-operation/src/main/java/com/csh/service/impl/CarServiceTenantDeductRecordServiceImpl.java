package com.csh.service.impl; 

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.CarServiceTenantDeductRecord;
import com.csh.entity.commonenum.CommonEnum.ChargeStatus;
import com.csh.dao.CarServiceTenantDeductRecordDao;
import com.csh.service.CarServiceTenantDeductRecordService;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("carServiceTenantDeductRecordServiceImpl")
public class CarServiceTenantDeductRecordServiceImpl extends BaseServiceImpl<CarServiceTenantDeductRecord,Long> implements CarServiceTenantDeductRecordService {

      @Resource(name="carServiceTenantDeductRecordDaoImpl")
      private CarServiceTenantDeductRecordDao carServiceTenantDeductRecordDao;
      @Resource
      public void setBaseDao(CarServiceTenantDeductRecordDao carServiceTenantDeductRecordDao) {
         super.setBaseDao(carServiceTenantDeductRecordDao);
  }


}