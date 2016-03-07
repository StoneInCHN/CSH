package com.csh.dao.impl;

import org.springframework.stereotype.Repository;

import com.csh.dao.MetaPropertyDao;
import com.csh.entity.MetaProperty;
import com.csh.framework.dao.impl.BaseDaoImpl;

/**
 * DaoImpl - 配置元属性
 * @author yohu
 *
 */
@Repository("metaPropertyDaoImpl")
public class MetaPropertyDaoImpl extends BaseDaoImpl<MetaProperty, Long> implements MetaPropertyDao {

}
