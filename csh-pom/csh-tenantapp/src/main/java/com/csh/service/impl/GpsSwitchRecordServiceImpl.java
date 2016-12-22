package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.GpsSwitchRecord;
import com.csh.dao.GpsSwitchRecordDao;
import com.csh.service.GpsSwitchRecordService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("gpsSwitchRecordServiceImpl")
public class GpsSwitchRecordServiceImpl extends BaseServiceImpl<GpsSwitchRecord,Long> implements GpsSwitchRecordService {

      @Resource(name="gpsSwitchRecordDaoImpl")
      public void setBaseDao(GpsSwitchRecordDao gpsSwitchRecordDao) {
         super.setBaseDao(gpsSwitchRecordDao);
  }
}