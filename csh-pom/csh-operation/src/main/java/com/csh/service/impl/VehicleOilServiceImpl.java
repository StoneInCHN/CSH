package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.VehicleOil;
import com.csh.dao.VehicleOilDao;
import com.csh.service.VehicleOilService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("vehicleOilServiceImpl")
public class VehicleOilServiceImpl extends BaseServiceImpl<VehicleOil,Long> implements VehicleOilService {

      @Resource(name="vehicleOilDaoImpl")
      public void setBaseDao(VehicleOilDao vehicleOilDao) {
         super.setBaseDao(vehicleOilDao);
  }
}