package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.Vehicle;
import com.csh.dao.VehicleDao;
import com.csh.service.VehicleService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("vehicleServiceImpl")
public class VehicleServiceImpl extends BaseServiceImpl<Vehicle,Long> implements VehicleService {

      @Resource(name="vehicleDaoImpl")
      public void setBaseDao(VehicleDao vehicleDao) {
         super.setBaseDao(vehicleDao);
  }
}