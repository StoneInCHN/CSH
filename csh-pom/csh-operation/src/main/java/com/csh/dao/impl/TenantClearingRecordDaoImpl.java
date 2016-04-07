package com.csh.dao.impl; 

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.FlushModeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.xml.crypto.Data;

import org.springframework.stereotype.Repository;

import com.csh.dao.TenantClearingRecordDao;
import com.csh.entity.Department;
import com.csh.entity.TenantClearingRecord;
import com.csh.framework.dao.impl.BaseDaoImpl;
@Repository("tenantClearingRecordDaoImpl")
public class TenantClearingRecordDaoImpl extends  BaseDaoImpl<TenantClearingRecord,Long> implements TenantClearingRecordDao {

  @Override
  public Date findLastPeriodEndDate ()
  {
    String jpql =
        "select max(periodEndDate) from TenantClearingRecord tcr where tcr.tenantID=tenantid";
    Query query = entityManager.createQuery (jpql);
    entityManager.setProperty ("tenantid", 1);;
    List<Date> dates = query.getResultList ();
    if (dates != null && dates.size () == 1)
    {
     return dates.get (0);
    }
    return null;
  }

}