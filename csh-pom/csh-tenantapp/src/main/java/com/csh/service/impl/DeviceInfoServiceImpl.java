package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.DeviceInfo;
import com.csh.dao.DeviceInfoDao;
import com.csh.service.DeviceInfoService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("deviceInfoServiceImpl")
public class DeviceInfoServiceImpl extends BaseServiceImpl<DeviceInfo,Long> implements DeviceInfoService {

      @Resource(name="deviceInfoDaoImpl")
      public void setBaseDao(DeviceInfoDao deviceInfoDao) {
         super.setBaseDao(deviceInfoDao);
  }
}