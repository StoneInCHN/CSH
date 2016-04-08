package com.csh.service; 

import com.csh.beans.OilBean;
import com.csh.entity.VehicleOil;
import com.csh.framework.service.BaseService;

public interface VehicleOilService extends BaseService<VehicleOil,Long>{

  void updateOilInfo(OilBean oil);
  
}