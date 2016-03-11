package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.VehicleDtc;
import com.csh.dao.VehicleDtcDao;
import com.csh.service.VehicleDtcService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("vehicleDtcServiceImpl")
public class VehicleDtcServiceImpl extends BaseServiceImpl<VehicleDtc,Long> implements VehicleDtcService {

      @Resource(name="vehicleDtcDaoImpl")
      public void setBaseDao(VehicleDtcDao vehicleDtcDao) {
         super.setBaseDao(vehicleDtcDao);
  }
}