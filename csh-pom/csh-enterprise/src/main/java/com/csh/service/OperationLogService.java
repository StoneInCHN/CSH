package com.csh.service;

import com.csh.entity.OperationLog;
import com.csh.framework.service.BaseService;

/**
 * 日志
 */
public interface OperationLogService extends BaseService<OperationLog, Long> {

  /**
   * 清空日志
   */
  void clear();

}
