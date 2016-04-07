package com.csh.dao;

import com.csh.entity.App;
import com.csh.framework.dao.BaseDao;

public interface AppDao extends BaseDao<App, Long> {

  public App getTenantAppById(Long tenantId);
}
