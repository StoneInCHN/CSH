package com.csh.service;

import java.util.Map;

import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;


public interface TenantInfoJdbcService {

  /**
   * 按服务类别获取商家列表（如category为空，获取所有商家），根据经纬度计算距离排序
   * 
   * @param lng 经度
   * @param lat 纬度
   * @param pageable 分页信息
   * @param radius 搜索半径
   * @param categoryId 服务类别ID
   * @return
   */
  public Page<Map<String, Object>> getTenantInfos(String lng, String lat, Pageable pageable,
      int radius, Long categoryId, Long tenantId);


}
