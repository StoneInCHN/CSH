package com.csh.dao.impl;

import org.springframework.stereotype.Repository;

import com.csh.dao.SystemConfigDao;
import com.csh.entity.SystemConfig;
import com.csh.framework.dao.impl.BaseDaoImpl;

/**
 * 数据字典
 * @author sujinxuan
 *
 */
@Repository("systemConfigDaoImpl")
public class SystemConfigDaoImpl extends BaseDaoImpl<SystemConfig, Long> implements SystemConfigDao {

}
