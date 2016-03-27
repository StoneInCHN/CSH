package com.csh.service.impl; 

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.VehicleInsuranceDao;
import com.csh.entity.VehicleInsurance;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.VehicleInsuranceService;

@Service("vehicleInsuranceServiceImpl")
public class VehicleInsuranceServiceImpl extends BaseServiceImpl<VehicleInsurance,Long> implements VehicleInsuranceService {

      @Resource(name="vehicleInsuranceDaoImpl")
      private VehicleInsuranceDao vehicleInsuranceDao;
      @Resource
      public void setBaseDao(VehicleInsuranceDao vehicleInsuranceDao) {
         super.setBaseDao(vehicleInsuranceDao);
  }
}