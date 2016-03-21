package com.csh.dao;

import java.util.List;

import com.csh.entity.Advertisement;
import com.csh.framework.dao.BaseDao;

public interface AdvertisementDao extends BaseDao<Advertisement, Long> {


  /**
   * 首页广告
   * 
   * @param tenantId
   * @return
   */
  List<Advertisement> getAdvBanner(Long tenantId);
}
