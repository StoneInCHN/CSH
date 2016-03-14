package com.csh.service; 

import java.util.List;

import com.csh.entity.VehicleBrandDetail;
import com.csh.framework.service.BaseService;

public interface VehicleBrandDetailService extends BaseService<VehicleBrandDetail,Long>{
  public List<VehicleBrandDetail> findRoots(Integer count);
}