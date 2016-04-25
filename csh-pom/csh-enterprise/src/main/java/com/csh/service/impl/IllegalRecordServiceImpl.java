package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.IllegalRecord;
import com.csh.dao.IllegalRecordDao;
import com.csh.service.IllegalRecordService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("illegalRecordServiceImpl")
public class IllegalRecordServiceImpl extends BaseServiceImpl<IllegalRecord,Long> implements IllegalRecordService {

      @Resource(name="illegalRecordDaoImpl")
      public void setBaseDao(IllegalRecordDao illegalRecordDao) {
         super.setBaseDao(illegalRecordDao);
  }
}