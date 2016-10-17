package com.csh.service; 

import java.util.Date;
import java.util.List;

import org.springframework.ui.ModelMap;

import com.csh.entity.EndUser;
import com.csh.entity.commonenum.CommonEnum.AccountStatus;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.framework.service.BaseService;
import com.csh.json.request.EndUserRequest;

public interface EndUserService extends BaseService<EndUser,Long>{
  Long countUserByTenantID(Long tenantID);
  Page<EndUser> findEndUser(Pageable pageable, ModelMap model,
      Date beginDate, Date endDate, String userNameSearch,AccountStatus accountStatusSearch);
  void bulkSave (List<EndUserRequest> endUserRequest);
}