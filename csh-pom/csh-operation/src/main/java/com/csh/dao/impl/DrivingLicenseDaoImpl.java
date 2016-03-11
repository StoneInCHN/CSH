package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.DrivingLicense;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.DrivingLicenseDao;
@Repository("drivingLicenseDaoImpl")
public class DrivingLicenseDaoImpl extends  BaseDaoImpl<DrivingLicense,Long> implements DrivingLicenseDao {

}