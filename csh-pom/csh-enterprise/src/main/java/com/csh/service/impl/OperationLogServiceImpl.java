package com.csh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.impl.OperationLogDaoImpl;
import com.csh.entity.OperationLog;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.OperationLogService;

/**
 * 日志
 * 
 * @author shijun
 *
 */
@Service("operationLogServiceImpl")
public class OperationLogServiceImpl extends BaseServiceImpl<OperationLog, Long> implements OperationLogService {

  @Resource(name = "operationLogDaoImpl")
  private OperationLogDaoImpl operationLogDaoImpl;

  @Resource(name = "operationLogDaoImpl")
  public void setBaseDao(OperationLogDaoImpl operationLogDaoImpl) {
    super.setBaseDao(operationLogDaoImpl);
  }

  public void clear() {
    operationLogDaoImpl.removeAll();
  }

}
