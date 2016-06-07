package com.csh.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.VehicleOilDao;
import com.csh.entity.DeviceInfo;
import com.csh.entity.VehicleOil;
import com.csh.entity.commonenum.CommonEnum.OilType;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.VehicleOilService;

@Service("vehicleOilServiceImpl")
public class VehicleOilServiceImpl extends BaseServiceImpl<VehicleOil, Long> implements
    VehicleOilService {

  @Resource(name = "vehicleOilDaoImpl")
  private VehicleOilDao vehicleOilDao;

  @Resource(name = "vehicleOilDaoImpl")
  public void setBaseDao(VehicleOilDao vehicleOilDao) {
    super.setBaseDao(vehicleOilDao);
  }

  @Override
  public BigDecimal calOilCost(BigDecimal averageFuelConsumption, DeviceInfo deviceInfo) {
    String plate = deviceInfo.getVehicle().getPlate();
    if (plate == null) {
      return null;
    }
    plate = plate.substring(0, 1);
    OilType oilType = deviceInfo.getVehicle().getVehicleBrandDetail().getOilType();
    if (oilType == null) {
      // 默认93号汽油
      oilType = OilType.P93;
    }
    List<Filter> filters = new ArrayList<Filter>();
    Filter plateFilter = new Filter("shortPlate", Operator.eq, plate);
    Filter oilTypeFilter = new Filter("oilType", Operator.eq, oilType);
    filters.add(plateFilter);
    filters.add(oilTypeFilter);
    List<VehicleOil> oils = vehicleOilDao.findList(null, null, filters, null);
    if (oils == null || oils.size() != 1) {
      return null;
    }
    VehicleOil oil = oils.get(0);
    return averageFuelConsumption.multiply(oil.getPrice()).setScale(2, BigDecimal.ROUND_HALF_UP);
  }
}
