package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.estore.Parameter;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.ParameterDao;
@Repository("parameterDaoImpl")
public class ParameterDaoImpl extends  BaseDaoImpl<Parameter,Long> implements ParameterDao {

}