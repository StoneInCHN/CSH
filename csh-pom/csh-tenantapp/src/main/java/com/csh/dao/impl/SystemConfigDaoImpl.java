package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.SystemConfig;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.SystemConfigDao;
@Repository("systemConfigDaoImpl")
public class SystemConfigDaoImpl extends  BaseDaoImpl<SystemConfig,Long> implements SystemConfigDao {

}