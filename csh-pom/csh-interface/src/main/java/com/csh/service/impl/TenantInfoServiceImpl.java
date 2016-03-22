package com.csh.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.BooleanUtils;
import org.springframework.stereotype.Service;

import com.csh.dao.EndUserDao;
import com.csh.dao.TenantInfoDao;
import com.csh.entity.CarService;
import com.csh.entity.EndUser;
import com.csh.entity.TenantInfo;
import com.csh.entity.Vehicle;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.TenantInfoService;
import com.csh.utils.FieldFilterUtils;

@Service("tenantInfoServiceImpl")
public class TenantInfoServiceImpl extends BaseServiceImpl<TenantInfo, Long> implements
    TenantInfoService {

  @Resource(name = "tenantInfoDaoImpl")
  private TenantInfoDao tenantInfoDao;

  @Resource(name = "endUserDaoImpl")
  private EndUserDao endUserDao;


  @Resource(name = "tenantInfoDaoImpl")
  public void setBaseDao(TenantInfoDao tenantInfoDao) {
    super.setBaseDao(tenantInfoDao);
  }

  @Override
  public Map<String, Object> getTenantByUserAndServiceCategory(Long userId, Long categoryId) {
    EndUser endUser = endUserDao.find(userId);
    Long tenantId = 0l;
    for (Vehicle vehicle : endUser.getVehicles()) {
      if (BooleanUtils.isTrue(vehicle.getIsDefault())) {
        tenantId = vehicle.getTenantID();
      }
    }
    TenantInfo tenantInfo = tenantInfoDao.find(tenantId);
    String[] properties =
        {"id", "tenantName", "contactPhone", "address", "businessTime", "photo", "latitude",
            "longitude"};
    Map<String, Object> map = FieldFilterUtils.filterEntityMap(properties, tenantInfo);

    List<CarService> carServices = new ArrayList<CarService>();
    for (CarService carService : tenantInfo.getCarServices()) {
      if (carService.getServiceCategory().getId().equals(categoryId)) {
        carServices.add(carService);
      }
    }
    String[] service_properties =
        {"id", "serviceName", "price", "promotionPrice", "serviceStatus",
            "serviceCategory.categoryName"};
    List<Map<String, Object>> service_map =
        FieldFilterUtils.filterCollectionMap(service_properties, carServices);
    map.put("carServices", service_map);
    return map;
  }
}
