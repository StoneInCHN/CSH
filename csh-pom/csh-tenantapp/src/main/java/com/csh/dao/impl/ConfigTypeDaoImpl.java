package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.ConfigType;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.ConfigTypeDao;
@Repository("configTypeDaoImpl")
public class ConfigTypeDaoImpl extends  BaseDaoImpl<ConfigType,Long> implements ConfigTypeDao {

}