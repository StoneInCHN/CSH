package com.csh.service; 

import java.util.List;
import java.util.Map;

import com.csh.entity.DeviceType;
import com.csh.framework.service.BaseService;

public interface DeviceTypeService extends BaseService<DeviceType,Long>{

  List<Map<String, Object>> findAllDeviceType ();

}