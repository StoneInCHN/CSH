package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.estore.ParameterGroup;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.ParameterGroupDao;
@Repository("parameterGroupDaoImpl")
public class ParameterGroupDaoImpl extends  BaseDaoImpl<ParameterGroup,Long> implements ParameterGroupDao {

}