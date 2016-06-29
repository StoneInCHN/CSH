package com.csh.service;

import java.util.List;
import java.util.Map;

import com.csh.entity.Advertisement;
import com.csh.framework.service.BaseService;

public interface AdvertisementService extends BaseService<Advertisement, Long> {

  /**
   * 首页广告
   * 
   * @param userLong
   * @return
   */
  List<Map<String, Object>> getAdvBanner(Long userId);

  /**
   * 获取加载页广告
   * 
   * @param piWeight
   * @param piHeight
   * @return
   */
  Advertisement getLoadAdv(Integer piWidth, Integer piHeight);
}
