package com.csh.dao;

import com.csh.entity.ResolutionConfig;
import com.csh.framework.dao.BaseDao;

public interface ResolutionConfigDao extends BaseDao<ResolutionConfig, Long> {

  /**
   * 根据分辨率获取app加载页广告最接近的分辨率配置
   * 
   * @param piWeight
   * @param piHeight
   * @return
   */
  ResolutionConfig getResolutionConfig(Integer piWidth, Integer piHeight);
}
