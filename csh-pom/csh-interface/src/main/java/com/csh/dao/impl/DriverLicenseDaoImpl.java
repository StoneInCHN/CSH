package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.DriverLicense;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.DriverLicenseDao;
@Repository("driverLicenseDaoImpl")
public class DriverLicenseDaoImpl extends  BaseDaoImpl<DriverLicense,Long> implements DriverLicenseDao {

}