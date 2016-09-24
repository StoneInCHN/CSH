package com.csh.dao.impl; 

import org.springframework.stereotype.Repository;

import com.csh.dao.FaultCodeDao;
import com.csh.entity.FaultCode;
import com.csh.framework.dao.impl.BaseDaoImpl;
@Repository("faultCodeDaoImpl")
public class FaultCodeDaoImpl extends  BaseDaoImpl<FaultCode,Long> implements FaultCodeDao {
	
}