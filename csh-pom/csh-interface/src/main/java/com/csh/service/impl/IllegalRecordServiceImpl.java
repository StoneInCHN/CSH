package com.csh.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csh.beans.Setting;
import com.csh.dao.IllegalRecordDao;
import com.csh.dao.ProvinceDao;
import com.csh.dao.VehicleDao;
import com.csh.entity.IllegalRecord;
import com.csh.entity.Province;
import com.csh.entity.Vehicle;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.IllegalRecordService;
import com.csh.utils.ApiUtils;
import com.csh.utils.LatLonUtil;
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

  @Resource(name = "vehicleDaoImpl")
  private VehicleDao vehicleDao;

  @Resource(name = "provinceDaoImpl")
  private ProvinceDao provinceDao;


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
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
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

          // 删除原有数据
          for (IllegalRecord record : illegalRecords) {
            illegalRecordDao.remove(record);
          }

          return insertRecord(plate);
        }
      }

    } else {
      return insertRecord(plate);
    }

    return illegalRecordDao.getIllegalRecords(plate);
  }


  private List<IllegalRecord> insertRecord(String plate) {

    Setting setting = SettingUtils.get();
    // insert新数据
    String lsPrefix = plate.substring(0, 1);
    String lsNum = plate.substring(1);
    Vehicle vehicle = vehicleDao.getVehicleByPlate(plate);
    Province province = provinceDao.getProvinceByShortName(lsPrefix);
    if (vehicle == null || province == null) {
      return null;
    }

    String url =
        setting.getIllegalSearchURL() + "?appkey=" + setting.getIllegalSearchAppKey() + "&carorg="
            + province.getPinyinName() + "&lsprefix=" + lsPrefix + "&lsnum=" + lsNum
            + "&lstype=02&frameno=" + vehicle.getVehicleNo();
    String res = ApiUtils.get(url);

    try {
      ObjectMapper mapper = new ObjectMapper();
      Map<String, Object> resMap = (Map<String, Object>) mapper.readValue(res, Map.class);
      if (resMap.get("result") == null || resMap.get("result").equals("")) {
        return null;
      }
      Map<String, Object> resultMap = (Map<String, Object>) resMap.get("result");
      List<Map<String, Object>> listMap = (List<Map<String, Object>>) resultMap.get("list");
      List<IllegalRecord> list = new ArrayList<IllegalRecord>();
      for (Map<String, Object> map : listMap) {
        IllegalRecord illegalRecord = new IllegalRecord();
        illegalRecord.setPlate(plate);
        illegalRecord.setIllegalAddress(map.get("address").toString());
        Map<String, Object> locMap = LatLonUtil.convertAddress(illegalRecord.getIllegalAddress());
        illegalRecord.setLng(Double.parseDouble(locMap.get("lng").toString()));
        illegalRecord.setLat(Double.parseDouble(locMap.get("lat").toString()));
        illegalRecord.setIllegalContent(map.get("content").toString());
        illegalRecord.setIllegalDate(map.get("time").toString());
        illegalRecord.setIllegalId(map.get("illegalid").toString());
        illegalRecord.setProcessingSite(map.get("agency") != null ? map.get("agency").toString()
            : null);
        illegalRecord.setScore(Integer.parseInt(map.get("score").toString()));
        illegalRecord.setFinesAmount(Double.parseDouble(map.get("price").toString()));
        illegalRecordDao.persist(illegalRecord);
        list.add(illegalRecord);
      }
      return list;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
