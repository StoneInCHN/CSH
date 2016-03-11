package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.VersionConfig;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.VersionConfigDao;
@Repository("versionConfigDaoImpl")
public class VersionConfigDaoImpl extends  BaseDaoImpl<VersionConfig,Long> implements VersionConfigDao {

}