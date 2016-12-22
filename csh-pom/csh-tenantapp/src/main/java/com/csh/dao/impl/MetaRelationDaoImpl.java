package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.MetaRelation;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.MetaRelationDao;
@Repository("metaRelationDaoImpl")
public class MetaRelationDaoImpl extends  BaseDaoImpl<MetaRelation,Long> implements MetaRelationDao {

}