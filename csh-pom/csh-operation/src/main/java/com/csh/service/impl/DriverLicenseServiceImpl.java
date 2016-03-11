package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.DriverLicense;
import com.csh.dao.DriverLicenseDao;
import com.csh.service.DriverLicenseService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("driverLicenseServiceImpl")
public class DriverLicenseServiceImpl extends BaseServiceImpl<DriverLicense,Long> implements DriverLicenseService {

      @Resource(name="driverLicenseDaoImpl")
      public void setBaseDao(DriverLicenseDao driverLicenseDao) {
         super.setBaseDao(driverLicenseDao);
  }
}