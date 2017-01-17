package com.csh.dao;

import com.csh.entity.TenantAccount;
import com.csh.entity.commonenum.CommonEnum.AppPlatform;
import com.csh.framework.dao.BaseDao;

public interface TenantAccountDao extends BaseDao<TenantAccount, Long> {
  /**
   * 获取租户登录token
   * 
   * @return
   */
  String getTenantUserToken(Long id);

  /**
   * 创建终端用户租户登录token
   */
  String createTenantUserToken(String token, Long id);

  /**
   * 删除租户用户登录token
   */
  void deleteTenantUserToken(Long id);

  /**
   * 根据名字和机构代码查询用户
   * 
   * @param name
   * @param orgCode
   * @return
   */
  public TenantAccount findByNameAndOrgCode(String name, String orgCode);

  /**
   * 获取登录租户APP手机平台
   * 
   * @param id
   * @return
   */
  AppPlatform getTenantUserAppPlatform(Long id);

  /**
   * 创建登录租户APP手机平台
   * 
   * @param appPlatform
   * @param id
   * @return
   */
  AppPlatform createTenantUserAppPlatform(AppPlatform appPlatform, Long id);
}
