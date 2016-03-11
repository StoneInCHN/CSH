package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.LoginStatistics;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.LoginStatisticsDao;
@Repository("loginStatisticsDaoImpl")
public class LoginStatisticsDaoImpl extends  BaseDaoImpl<LoginStatistics,Long> implements LoginStatisticsDao {

}