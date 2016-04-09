package com.csh.dao;

import com.csh.entity.Province;
import com.csh.framework.dao.BaseDao;

public interface ProvinceDao extends BaseDao<Province, Long> {


  /**
   * 根据简称查询省份
   * 
   * @param shortName
   * @return
   */
  Province getProvinceByShortName(String shortName);
}
