package com.csh.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.csh.entity.SystemConfig;
import com.csh.entity.commonenum.CommonEnum.ConfigKey;
import com.csh.framework.ordering.Ordering.Direction;
import com.csh.framework.service.BaseService;


/**
 * 数据字典
 * 
 * @author sujinxuan
 *
 */
public interface SystemConfigService extends BaseService<SystemConfig, Long> {

  /**
   * 获取系统配置
   * 
   * @param configKey
   * @param direction
   * @return
   */
  List<Map<String, Object>> findByConfigKey(ConfigKey configKey, Direction direction);

  /**
   * 根据当前日期获取下一个结算日期
   * 
   * @param currentDate
   * @return
   */
  Map<String, Object> getBillingDate(Date currentDate);

  /**
   * 通过配置项获取对应的配置值
   * 
   * @param configKey
   * @return
   */
  List<SystemConfig> getListConfigValueByKey(int configKey);


}
