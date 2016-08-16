package com.csh.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.BooleanUtils;
import org.springframework.stereotype.Service;

import com.csh.dao.CarServiceDao;
import com.csh.entity.CarService;
import com.csh.entity.EndUser;
import com.csh.entity.TenantInfo;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.CarServiceService;

@Service("carServiceServiceImpl")
public class CarServiceServiceImpl extends BaseServiceImpl<CarService, Long> implements
    CarServiceService {

  @Resource(name = "carServiceDaoImpl")
  private CarServiceDao carServiceDao;

  @Resource(name = "carServiceDaoImpl")
  public void setBaseDao(CarServiceDao carServiceDao) {
    super.setBaseDao(carServiceDao);
  }

  @Override
  public List<CarService> getServicesByTenantAndCategory(TenantInfo tenantInfo, Long categoryId) {
    return carServiceDao.getServicesByTenantAndCategory(tenantInfo, categoryId);
  }

  @Override
  public Map<String, Object> getGiftAmount(CarService carService, EndUser endUser) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (BooleanUtils.isTrue(carService.getIsAllowedRedPackage())) {
      if (carService.getRedPackageMax() != null
          && carService.getRedPackageMax().compareTo(new BigDecimal(0)) > 0) {
        map.put("redPacketAmount", carService.getRedPackageMax().toString());
        if (carService.getRedPackageMax().compareTo(endUser.getWallet().getGiftAmount()) > 0) {
          map.put("redPacketAmount", endUser.getWallet().getGiftAmount().toString());
        }
      } else {
        map.put("redPacketAmount", "0");
      }
    } else {
      map.put("redPacketAmount", "0");
    }
    return map;
  }
}
