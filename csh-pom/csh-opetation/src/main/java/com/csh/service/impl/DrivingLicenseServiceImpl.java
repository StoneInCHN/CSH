package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.DrivingLicense;
import com.csh.dao.DrivingLicenseDao;
import com.csh.service.DrivingLicenseService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("drivingLicenseServiceImpl")
public class DrivingLicenseServiceImpl extends BaseServiceImpl<DrivingLicense,Long> implements DrivingLicenseService {

      @Resource(name="drivingLicenseDaoImpl")
      public void setBaseDao(DrivingLicenseDao drivingLicenseDao) {
         super.setBaseDao(drivingLicenseDao);
  }
}