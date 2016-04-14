package com.csh.dao;

import java.util.Date;

import org.springframework.ui.ModelMap;

import com.csh.entity.EndUser;
import com.csh.entity.commonenum.CommonEnum.AccountStatus;
import com.csh.framework.dao.BaseDao;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;

public interface EndUserDao extends BaseDao<EndUser, Long>
{
  Long countUserByTenantID (Long tenantID);

  public Page<EndUser> findEndUser (Pageable pageable, ModelMap model,
      Date beginDate, Date endDate, String userNameSearch,
      AccountStatus accountStatusSearch,Long tenantID);
}