package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.DeviceInfo;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.DeviceInfoDao;
@Repository("deviceInfoDaoImpl")
public class DeviceInfoDaoImpl extends  BaseDaoImpl<DeviceInfo,Long> implements DeviceInfoDao {

}