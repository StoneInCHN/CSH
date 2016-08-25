package com.csh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.ReportProcedureDao;
import com.csh.entity.base.BaseEntity;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.ReportProcedureService;

/**
 * 
 * @author yohu
 *
 */
@Service ("reportProcedureServiceImpl")
public class ReportProcedureServiceImpl extends
    BaseServiceImpl<BaseEntity, Long> implements
    ReportProcedureService
{

  @Resource (name = "reportProcedureDaoImpl")
  private void setBaseDao (ReportProcedureDao reportProcedureDao)
  {
    super.setBaseDao (reportProcedureDao);
  }
}
