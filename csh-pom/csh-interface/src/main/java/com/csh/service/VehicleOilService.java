package com.csh.service;

import java.math.BigDecimal;

import com.csh.entity.DeviceInfo;
import com.csh.entity.VehicleOil;
import com.csh.framework.service.BaseService;

public interface VehicleOilService extends BaseService<VehicleOil, Long> {

  /**
   * 计算油费支出
   * 
   * @param averageFuelConsumption 平均油耗
   * @param deviceInfo 设备
   * @return
   */
  public BigDecimal calOilCost(BigDecimal averageFuelConsumption, DeviceInfo deviceInfo);
}
