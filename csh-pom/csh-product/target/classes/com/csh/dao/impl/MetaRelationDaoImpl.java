package com.csh.dao.impl;

import org.springframework.stereotype.Repository;

import com.csh.dao.MetaRelationDao;
import com.csh.entity.MetaRelation;
import com.csh.framework.dao.impl.BaseDaoImpl;

/**
 * DaoImpl - 配置元关系
 * @author yohu
 *
 */
@Repository("metaRelationDaoImpl")
public class MetaRelationDaoImpl extends BaseDaoImpl<MetaRelation, Long> implements MetaRelationDao {

}
