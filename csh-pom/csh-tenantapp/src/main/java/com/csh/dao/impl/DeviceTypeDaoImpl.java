package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.DeviceType;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.DeviceTypeDao;
@Repository("deviceTypeDaoImpl")
public class DeviceTypeDaoImpl extends  BaseDaoImpl<DeviceType,Long> implements DeviceTypeDao {

}