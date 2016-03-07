package com.csh.dao;

import com.csh.entity.OperationLog;
import com.csh.framework.dao.BaseDao;

/**
 * 日志
 * 
 * @author shijun
 *
 */
public interface OperationLogDao extends BaseDao<OperationLog, Long> {

  /**
   * 删除所有日志
   */
  void removeAll();

}
