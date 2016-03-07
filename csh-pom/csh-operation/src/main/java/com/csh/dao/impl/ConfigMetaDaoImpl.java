package com.csh.dao.impl;

import org.springframework.stereotype.Repository;

import com.csh.dao.ConfigMetaDao;
import com.csh.entity.ConfigMeta;
import com.csh.framework.dao.impl.BaseDaoImpl;

/**
 * DaoImpl - 元数据
 * @author yohu
 *
 */
@Repository("configMetaDaoImpl")
public class ConfigMetaDaoImpl extends BaseDaoImpl<ConfigMeta, Long> implements ConfigMetaDao {

}
