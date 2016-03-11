package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.OperationLog;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.OperationLogDao;
@Repository("operationLogDaoImpl")
public class OperationLogDaoImpl extends  BaseDaoImpl<OperationLog,Long> implements OperationLogDao {

}