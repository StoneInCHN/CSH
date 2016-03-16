package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.VehicleBrandDetail;
import com.csh.dao.VehicleBrandDetailDao;
import com.csh.service.VehicleBrandDetailService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("vehicleBrandDetailServiceImpl")
public class VehicleBrandDetailServiceImpl extends BaseServiceImpl<VehicleBrandDetail,Long> implements VehicleBrandDetailService {

      @Resource(name="vehicleBrandDetailDaoImpl")
      public void setBaseDao(VehicleBrandDetailDao vehicleBrandDetailDao) {
         super.setBaseDao(vehicleBrandDetailDao);
  }
}