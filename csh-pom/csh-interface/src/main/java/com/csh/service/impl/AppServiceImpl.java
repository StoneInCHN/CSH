package com.csh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.AppDao;
import com.csh.entity.App;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.AppService;

@Service("appServiceImpl")
public class AppServiceImpl extends BaseServiceImpl<App, Long> implements AppService {

  @Resource(name = "appDaoImpl")
  private AppDao appDao;

  @Resource(name = "appDaoImpl")
  public void setBaseDao(AppDao appDao) {
    super.setBaseDao(appDao);
  }

  @Override
  public App getTenantAppById(Long tenantId) {
    return appDao.getTenantAppById(tenantId);
  }
}
