package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.estore.Attribute;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.AttributeDao;
@Repository("attributeDaoImpl")
public class AttributeDaoImpl extends  BaseDaoImpl<Attribute,Long> implements AttributeDao {

}