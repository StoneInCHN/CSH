package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.VehicleLine;
import com.csh.dao.VehicleLineDao;
import com.csh.service.VehicleLineService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("vehicleLineServiceImpl")
public class VehicleLineServiceImpl extends BaseServiceImpl<VehicleLine,Long> implements VehicleLineService {

      @Resource(name="vehicleLineDaoImpl")
      public void setBaseDao(VehicleLineDao vehicleLineDao) {
         super.setBaseDao(vehicleLineDao);
  }
}