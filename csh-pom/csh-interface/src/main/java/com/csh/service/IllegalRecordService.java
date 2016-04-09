package com.csh.service;

import java.util.List;

import com.csh.entity.IllegalRecord;
import com.csh.framework.service.BaseService;

/**
 * 违章查询
 * 
 * @author shijun
 *
 */
public interface IllegalRecordService extends BaseService<IllegalRecord, Long> {

  /**
   * 通过车牌查违章
   * 
   * @param plate
   * @return
   */
  public List<IllegalRecord> getIllegalRecords(String plate);

}
