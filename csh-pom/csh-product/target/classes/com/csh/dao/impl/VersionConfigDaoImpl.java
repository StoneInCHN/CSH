package com.csh.dao.impl;

import org.springframework.stereotype.Repository;

import com.csh.dao.VersionConfigDao;
import com.csh.entity.VersionConfig;
import com.csh.framework.dao.impl.BaseDaoImpl;

/**
 * DaoImpl - 版本配置
 * @author yohu
 *
 */
@Repository("versionConfigDaoImpl")
public class VersionConfigDaoImpl extends BaseDaoImpl<VersionConfig, Long> implements VersionConfigDao {

}
