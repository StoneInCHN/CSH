package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.FaultCode;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.FaultCodeDao;
@Repository("faultCodeDaoImpl")
public class FaultCodeDaoImpl extends  BaseDaoImpl<FaultCode,Long> implements FaultCodeDao {

}