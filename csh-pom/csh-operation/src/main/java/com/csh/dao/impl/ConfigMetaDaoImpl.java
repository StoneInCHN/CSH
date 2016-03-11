package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.ConfigMeta;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.ConfigMetaDao;
@Repository("configMetaDaoImpl")
public class ConfigMetaDaoImpl extends  BaseDaoImpl<ConfigMeta,Long> implements ConfigMetaDao {

}