package com.csh.service;

import java.util.Map;

import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;


public interface TenantInfoJdbcService {

  /**
   * 按服务类别获取商家列表（如category为空，获取所有商家），根据经纬度计算距离排序
   * 
   * @param lng
   * @param lat
   * @param pageable
   * @param radius
   * @param categoryId
   * @return
   */
  public Page<Map<String, Object>> getTenantInfos(String lng, String lat, Pageable pageable,
      int radius, Long categoryId);

}
