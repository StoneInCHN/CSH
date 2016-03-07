package com.csh.dao;

import com.csh.entity.TenantInfo;
import com.csh.framework.dao.BaseDao;

/**
 * 租户信息
 * 
 * @author shijun
 *
 */
public interface TenantInfoDao extends BaseDao<TenantInfo, Long> {

  /**
   * 通过机构代码找租户
   * 
   * @param orgCode
   * @return
   */
  public TenantInfo findTenantWithOrgCode(String orgCode);
}
