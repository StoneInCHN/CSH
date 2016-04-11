package com.csh.service; 

import java.math.BigDecimal;

import com.csh.entity.VehicleOil;
import com.csh.entity.commonenum.CommonEnum.OilType;
import com.csh.framework.service.BaseService;

public interface VehicleOilService extends BaseService<VehicleOil,Long>{

  BigDecimal getOidPrice (OilType oilType, String shortPlate);

}