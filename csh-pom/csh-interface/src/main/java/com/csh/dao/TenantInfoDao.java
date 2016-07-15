package com.csh.dao;

import com.csh.entity.TenantInfo;
import com.csh.framework.dao.BaseDao;

public interface TenantInfoDao extends BaseDao<TenantInfo, Long> {

  /**
   * 通过机构代码找租户
   * 
   * @param orgCode
   * @return
   */
  TenantInfo findTenantWithOrgCode(String orgCode);
}
