package com.csh.dao.impl;

import org.springframework.stereotype.Repository;

import com.csh.dao.ReportProcedureDao;
import com.csh.entity.base.BaseEntity;
import com.csh.framework.dao.impl.BaseDaoImpl;

/**
 * DaoImpl - 报表存储过程调用
 * @author yohu
 *
 */
@Repository("reportProcedureDaoImpl")
public class ReportProcedureDaoImpl extends BaseDaoImpl<BaseEntity, Long> implements ReportProcedureDao {


}
