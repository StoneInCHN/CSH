package com.csh.dao.impl;

import javax.persistence.FlushModeType;

import org.springframework.stereotype.Repository;

import com.csh.dao.OperationLogDao;
import com.csh.entity.OperationLog;
import com.csh.framework.dao.impl.BaseDaoImpl;

/**
 * 日志
 * 
 * @author shijun
 *
 */
@Repository("operationLogDaoImpl")
public class OperationLogDaoImpl extends BaseDaoImpl<OperationLog, Long> implements OperationLogDao {

  public void removeAll() {
    String jpql = "delete from Log log";
    entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT).executeUpdate();
  }

}
