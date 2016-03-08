package com.csh.dao.impl;

import org.springframework.stereotype.Repository;

import com.csh.dao.ConfigTypeDao;
import com.csh.entity.ConfigType;
import com.csh.framework.dao.impl.BaseDaoImpl;

/**
 * DaoImpl - 元数据
 * @author yohu
 *
 */
@Repository("configTypeDaoImpl")
public class ConfigTypeDaoImpl extends BaseDaoImpl<ConfigType, Long> implements ConfigTypeDao {

}
