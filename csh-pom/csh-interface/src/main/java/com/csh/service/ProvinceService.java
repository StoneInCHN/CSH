package com.csh.service;

import com.csh.entity.Province;
import com.csh.framework.service.BaseService;

public interface ProvinceService extends BaseService<Province, Long> {

  /**
   * 根据简称查询省份
   * 
   * @param shortName
   * @return
   */
  Province getProvinceByShortName(String shortName);
}
