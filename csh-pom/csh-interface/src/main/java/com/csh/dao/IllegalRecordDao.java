package com.csh.dao;

import java.util.List;

import com.csh.entity.IllegalRecord;
import com.csh.framework.dao.BaseDao;

/**
 * 违章查询
 * 
 * @author shijun
 *
 */
public interface IllegalRecordDao extends BaseDao<IllegalRecord, Long>{
  
  /**
   * 通过车牌查违章
   * 
   * @param plate
   * @return
   */
  public List<IllegalRecord> getIllegalRecords(String plate);

}
