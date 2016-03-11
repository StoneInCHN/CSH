package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.EndUser;
import com.csh.dao.EndUserDao;
import com.csh.service.EndUserService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("endUserServiceImpl")
public class EndUserServiceImpl extends BaseServiceImpl<EndUser,Long> implements EndUserService {

      @Resource(name="endUserDaoImpl")
      public void setBaseDao(EndUserDao endUserDao) {
         super.setBaseDao(endUserDao);
  }
}