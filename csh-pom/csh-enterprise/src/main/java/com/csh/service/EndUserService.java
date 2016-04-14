package com.csh.service; 

import java.util.Date;

import org.springframework.ui.ModelMap;

import com.csh.entity.EndUser;
import com.csh.entity.commonenum.CommonEnum.AccountStatus;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.framework.service.BaseService;

public interface EndUserService extends BaseService<EndUser,Long>{
  Long countUserByTenantID(Long tenantID);
  Page<EndUser> findEndUser(Pageable pageable, ModelMap model,
      Date beginDate, Date endDate, String userNameSearch,AccountStatus accountStatusSearch);
}