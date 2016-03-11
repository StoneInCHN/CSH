package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.PluginConfig;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.PluginConfigDao;
@Repository("pluginConfigDaoImpl")
public class PluginConfigDaoImpl extends  BaseDaoImpl<PluginConfig,Long> implements PluginConfigDao {

}