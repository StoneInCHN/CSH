package com.csh.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.arjuna.ats.internal.jdbc.drivers.modifiers.list;
import com.csh.dao.TenantInfoDao;
import com.csh.entity.ConfigMeta;
import com.csh.entity.EndUser;
import com.csh.entity.TenantImage;
import com.csh.entity.TenantInfo;
import com.csh.entity.Vehicle;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.TenantAccountService;
import com.csh.service.TenantImageService;
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
  
  @Resource(name = "tenantImageServiceImpl")
  private TenantImageService tenantImageService;
  
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
    List<Filter> filters = new ArrayList<Filter> ();
    Filter plateFilter = new Filter();
    plateFilter.setOperator (Operator.ne);
    plateFilter.setProperty ("plate");
    plateFilter.setValue ("0000000");
    
    filters.add (plateFilter);
    
    List<Vehicle> vehicleList =vehicleService.findList (null, filters, null, true, null);
    for (Vehicle vehicle : vehicleList)
    {
      EndUser user = vehicle.getEndUser ();
      if (!endUserList.contains (user))
      {
        endUserList.add (user);
      }
    }
    return endUserList;
  }

  @Override
  @Transactional(propagation=Propagation.REQUIRED)
  public void saveTenantInfo (TenantInfo tenantInfo, String[] tenantImageList,Long[] deleteImageIdList)
  {
    this.update (tenantInfo, "tenantName","ownerName","address","createDate","orgCode","accountStatus",
        "versionConfig","area","storeLogo","license","photo","praiseRate","isHaveAccount","distributor","qrImage");
    for (String image : tenantImageList)
    {
      TenantImage tenantImage = new TenantImage ();
      tenantImage.setImage (image);
      tenantImage.setTenantInfo (tenantInfo);
      tenantImageService.save (tenantImage);
    }
    if (deleteImageIdList!= null && deleteImageIdList.length>0)
    {
      tenantImageService.delete (deleteImageIdList);
    }
  }
}
