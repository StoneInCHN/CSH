package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.VehicleBrand;
import com.csh.dao.VehicleBrandDao;
import com.csh.service.VehicleBrandService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("vehicleBrandServiceImpl")
public class VehicleBrandServiceImpl extends BaseServiceImpl<VehicleBrand,Long> implements VehicleBrandService {

      @Resource(name="vehicleBrandDaoImpl")
      public void setBaseDao(VehicleBrandDao vehicleBrandDao) {
         super.setBaseDao(vehicleBrandDao);
  }
}