package com.csh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.CarServiceDao;
import com.csh.entity.CarService;
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
}
