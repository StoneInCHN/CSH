package com.csh.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.stereotype.Service;

import com.csh.beans.Setting;
import com.csh.dao.IllegalRecordDao;
import com.csh.entity.IllegalRecord;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.IllegalRecordService;
import com.csh.utils.SettingUtils;
import com.csh.utils.TimeUtils;

/**
 * 违章查询
 * 
 * @author shijun
 *
 */
@Service("illegalRecordServiceImpl")
public class IllegalRecordServiceImpl extends BaseServiceImpl<IllegalRecord, Long> implements
    IllegalRecordService {

  @Resource(name = "illegalRecordDaoImpl")
  private IllegalRecordDao illegalRecordDao;


  @Resource(name = "illegalRecordDaoImpl")
  public void setBaseDao(IllegalRecordDao illegalRecordDao) {
    super.setBaseDao(illegalRecordDao);
  }

  /**
   * 通过车牌查违章
   * 
   * @param plate
   * @return
   */
  public List<IllegalRecord> getIllegalRecords(String plate) {

    List<IllegalRecord> illegalRecords = illegalRecordDao.getIllegalRecords(plate);

    if (null != illegalRecords && illegalRecords.size() > 0) {

      /**
       * 判断是否已经过了服务器配置的请求周期,如果过了再次请求如果没有过的话就返回现在数据库字段
       */
      Date lastCreateDate = illegalRecords.get(0).getCreateDate();

      String illegaPeriodStr = SettingUtils.get().getIllegalSearchPeriod();

      Integer illegaPeriodInt = 0;

      Date afterPeriodDate = null;

      if (illegaPeriodStr != null) {

        illegaPeriodInt = Integer.parseInt(illegaPeriodStr);

      } else {
        /**
         * 默认天数
         */
        illegaPeriodInt = 7;
      }

      afterPeriodDate = TimeUtils.addDays(illegaPeriodInt, lastCreateDate);

      if (afterPeriodDate != null) {
        if (afterPeriodDate.after(new Date())) {
          /**
           * 查数据库
           */
          return illegalRecords;
          
        } else {
          /**
           * 请求第三方数据并插入自己数据库
           */
          
        }
      }

    }



    return illegalRecordDao.getIllegalRecords(plate);
  }
}
