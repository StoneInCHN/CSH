package com.csh.service;

import java.util.List;
import java.util.Set;

import com.csh.entity.ConfigMeta;
import com.csh.entity.EndUser;
import com.csh.entity.TenantInfo;
import com.csh.framework.service.BaseService;

/**
 * 租户信息
 * 
 * @author shijun
 *
 */
public interface TenantInfoService extends BaseService<TenantInfo, Long> {

  /**
   * 通过机构代码找租户
   * 
   * @param orgCode
   * @return
   */
  public TenantInfo findTenantWithOrgCode(String orgCode);

  /**
   * 获取当前用户版本的功能包
   * 
   * @return
   */
  Set<ConfigMeta> getCurrentTenantVersionPackage();
  /**
   * 查询租户的所有终端用户
   */
  List<EndUser> findEndUser();
}
