package com.csh.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.BooleanUtils;
import org.springframework.stereotype.Service;

import com.csh.dao.AdvertisementDao;
import com.csh.dao.EndUserDao;
import com.csh.entity.Advertisement;
import com.csh.entity.EndUser;
import com.csh.entity.Vehicle;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.AdvertisementService;
import com.csh.utils.FieldFilterUtils;

@Service("advertisementServiceImpl")
public class AdvertisementServiceImpl extends BaseServiceImpl<Advertisement, Long> implements
    AdvertisementService {

  @Resource(name = "advertisementDaoImpl")
  private AdvertisementDao advertisementDao;

  @Resource(name = "endUserDaoImpl")
  private EndUserDao endUserDao;

  @Resource(name = "advertisementDaoImpl")
  public void setBaseDao(AdvertisementDao advertisementDao) {
    super.setBaseDao(advertisementDao);
  }

  @Override
  public List<Map<String, Object>> getAdvBanner(Long userId) {

    EndUser endUser = endUserDao.find(userId);
    Long tenantId = 0l;
    for (Vehicle vehicle : endUser.getVehicles()) {
      if (BooleanUtils.isTrue(vehicle.getIsDefault())) {
        tenantId = vehicle.getTenantID();
      }
    }
    List<Advertisement> advertisements = advertisementDao.getAdvBanner(tenantId);
    String[] properties = {"id", "advImageUrl", "advContentLink"};
    List<Map<String, Object>> map =
        FieldFilterUtils.filterCollectionMap(properties, advertisements);
    return map;
  }
}
