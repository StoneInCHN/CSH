package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.OperationLog;
import com.csh.dao.OperationLogDao;
import com.csh.service.OperationLogService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("operationLogServiceImpl")
public class OperationLogServiceImpl extends BaseServiceImpl<OperationLog,Long> implements OperationLogService {

      @Resource(name="operationLogDaoImpl")
      public void setBaseDao(OperationLogDao operationLogDao) {
         super.setBaseDao(operationLogDao);
  }
}