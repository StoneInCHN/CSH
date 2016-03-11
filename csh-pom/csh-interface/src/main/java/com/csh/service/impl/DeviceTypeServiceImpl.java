package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.DeviceType;
import com.csh.dao.DeviceTypeDao;
import com.csh.service.DeviceTypeService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("deviceTypeServiceImpl")
public class DeviceTypeServiceImpl extends BaseServiceImpl<DeviceType,Long> implements DeviceTypeService {

      @Resource(name="deviceTypeDaoImpl")
      public void setBaseDao(DeviceTypeDao deviceTypeDao) {
         super.setBaseDao(deviceTypeDao);
  }
}