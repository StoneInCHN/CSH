package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.VehicleInsurance;
import com.csh.dao.VehicleInsuranceDao;
import com.csh.service.VehicleInsuranceService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("vehicleInsuranceServiceImpl")
public class VehicleInsuranceServiceImpl extends BaseServiceImpl<VehicleInsurance,Long> implements VehicleInsuranceService {

      @Resource(name="vehicleInsuranceDaoImpl")
      public void setBaseDao(VehicleInsuranceDao vehicleInsuranceDao) {
         super.setBaseDao(vehicleInsuranceDao);
  }
}