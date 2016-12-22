package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.LoginStatistics;
import com.csh.dao.LoginStatisticsDao;
import com.csh.service.LoginStatisticsService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("loginStatisticsServiceImpl")
public class LoginStatisticsServiceImpl extends BaseServiceImpl<LoginStatistics,Long> implements LoginStatisticsService {

      @Resource(name="loginStatisticsDaoImpl")
      public void setBaseDao(LoginStatisticsDao loginStatisticsDao) {
         super.setBaseDao(loginStatisticsDao);
  }
}