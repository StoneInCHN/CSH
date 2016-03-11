package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.VehicleMaintain;
import com.csh.dao.VehicleMaintainDao;
import com.csh.service.VehicleMaintainService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("vehicleMaintainServiceImpl")
public class VehicleMaintainServiceImpl extends BaseServiceImpl<VehicleMaintain,Long> implements VehicleMaintainService {

      @Resource(name="vehicleMaintainDaoImpl")
      public void setBaseDao(VehicleMaintainDao vehicleMaintainDao) {
         super.setBaseDao(vehicleMaintainDao);
  }
}