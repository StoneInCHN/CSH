package com.csh.service.impl; 

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.VehicleBrandDao;
import com.csh.entity.VehicleBrand;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.VehicleBrandService;

@Service("vehicleBrandServiceImpl")
public class VehicleBrandServiceImpl extends BaseServiceImpl<VehicleBrand,Long> implements VehicleBrandService {

      @Resource(name="vehicleBrandDaoImpl")
      public void setBaseDao(VehicleBrandDao vehicleBrandDao) {
         super.setBaseDao(vehicleBrandDao);
  }
}