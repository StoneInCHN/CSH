package com.csh.dao; 
import java.util.List;

import com.csh.entity.VehicleBrandDetail;
import com.csh.framework.dao.BaseDao;

public interface VehicleBrandDetailDao extends  BaseDao<VehicleBrandDetail,Long>{
  public List<VehicleBrandDetail> findRoots(Integer count);
}