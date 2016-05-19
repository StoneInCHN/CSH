package com.csh.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.TenantInfoDao;
import com.csh.entity.ConfigMeta;
import com.csh.entity.EndUser;
import com.csh.entity.TenantInfo;
import com.csh.entity.Vehicle;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.TenantAccountService;
import com.csh.service.TenantInfoService;
import com.csh.service.VehicleService;

/**
 * 租户信息
 * 
 * @author shijun
 *
 */
@Service("tenantInfoServiceImpl")
public class TenantInfoServiceImpl extends BaseServiceImpl<TenantInfo, Long> implements
    TenantInfoService {


  @Resource(name = "tenantInfoDaoImpl")
  private TenantInfoDao tenantInfoDao;

  @Resource(name = "tenantInfoDaoImpl")
  public void setBaseDao(TenantInfoDao tenantInfoDao) {
    super.setBaseDao(tenantInfoDao);
  }

  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;
  @Resource(name = "vehicleServiceImpl")
  private VehicleService vehicleService;
  
  @Resource(name = "threadPoolExecutor")
  private Executor threadPoolExecutor;

 



 

  @Override
  public TenantInfo findTenantWithOrgCode(String orgCode) {
    return tenantInfoDao.findTenantWithOrgCode(orgCode);
  }

  /**
   * 获取租户的功能包
   */
  @Override
  public Set<ConfigMeta> getCurrentTenantVersionPackage() {
    TenantInfo tenantInfo = tenantAccountService.getCurrentTenantInfo();
    tenantInfo = tenantInfoDao.find(tenantInfo.getId());
    if (tenantInfo != null) {
      return tenantInfo.getVersionConfig().getConfigMeta();
    }
    return null;
  }

  /**
   * 查询租户下的所有终端用户
   */
  @Override
  public List<EndUser> findEndUser ()
  {
    List<EndUser> endUserList = new ArrayList<EndUser>();
    List<Vehicle> vehicleList =vehicleService.findList (null, null, null, true, null);
    for (Vehicle vehicle : vehicleList)
    {
      EndUser user = vehicle.getEndUser ();
      if (endUserList.contains (user))
      {
        endUserList.add (user);
      }
    }
    return endUserList;
  }
}
