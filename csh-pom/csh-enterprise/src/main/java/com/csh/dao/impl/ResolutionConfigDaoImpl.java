package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.ResolutionConfig;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.ResolutionConfigDao;
@Repository("resolutionConfigDaoImpl")
public class ResolutionConfigDaoImpl extends  BaseDaoImpl<ResolutionConfig,Long> implements ResolutionConfigDao {

}