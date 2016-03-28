package com.csh.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.BooleanUtils;
import org.springframework.stereotype.Service;

import com.csh.dao.EndUserDao;
import com.csh.dao.TenantInfoDao;
import com.csh.entity.CarService;
import com.csh.entity.DeviceInfo;
import com.csh.entity.EndUser;
import com.csh.entity.TenantInfo;
import com.csh.entity.Vehicle;
import com.csh.entity.commonenum.CommonEnum.ServiceStatus;
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
        DeviceInfo deviceInfo = vehicle.getDevice();
        if (deviceInfo != null) {
          tenantId = deviceInfo.getTenantID();
          break;
        }

      }
    }
    TenantInfo tenantInfo = tenantInfoDao.find(tenantId);
    if (tenantInfo == null) {
      return null;
    }
    String[] properties =
        {"id", "tenantName", "contactPhone", "address", "businessTime", "photo", "latitude",
            "longitude"};
    Map<String, Object> map = FieldFilterUtils.filterEntityMap(properties, tenantInfo);

    List<Map<String, Object>> service_map = new ArrayList<Map<String, Object>>();
    Set<String> categoryNames = new HashSet<String>();
    String[] service_properties = {"id", "serviceName", "price", "promotionPrice"};
    for (CarService carService : tenantInfo.getCarServices()) {
      if (carService.getServiceCategory().getId().equals(categoryId)) {
        List<Map<String, Object>> sub_service_maps = new ArrayList<Map<String, Object>>();
        Map<String, Object> sub_service_map = new HashMap<String, Object>();
        Map<String, Object> category_map = new HashMap<String, Object>();
        if (!categoryNames.contains(carService.getServiceCategory().getCategoryName())
            && ServiceStatus.ENABLED.equals(carService.getServiceStatus())) {
          categoryNames.add(carService.getServiceCategory().getCategoryName());
          category_map.put("categoryName", carService.getServiceCategory().getCategoryName());
          sub_service_map = FieldFilterUtils.filterEntityMap(service_properties, carService);

          service_map.add(category_map);
        } else {
          for (Map<String, Object> serviceMap : service_map) {
            if (serviceMap.get("categoryName").equals(
                carService.getServiceCategory().getCategoryName())) {
              sub_service_maps = (List<Map<String, Object>>) serviceMap.get("subServices");
              sub_service_map = FieldFilterUtils.filterEntityMap(service_properties, carService);

            }
          }
        }
        sub_service_maps.add(sub_service_map);
        category_map.put("subServices", sub_service_maps);

      }
    }
    map.put("carServices", service_map);
    return map;
  }
}
