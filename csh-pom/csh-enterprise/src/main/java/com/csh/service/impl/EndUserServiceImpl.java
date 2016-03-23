package com.csh.service.impl; 

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.EndUserDao;
import com.csh.entity.EndUser;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.EndUserService;

@Service("endUserServiceImpl")
public class EndUserServiceImpl extends BaseServiceImpl<EndUser,Long> implements EndUserService {

      @Resource(name="endUserDaoImpl")
      private EndUserDao endUserDao;
      @Resource
      public void setBaseDao(EndUserDao endUserDao) {
         super.setBaseDao(endUserDao);
  }

      @Override
      public Long countUserByTenantID (Long tenantID)
      {
        
        return endUserDao.countUserByTenantID (tenantID);
      }
}