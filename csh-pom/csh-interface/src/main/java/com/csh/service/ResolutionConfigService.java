package com.csh.service;

import com.csh.entity.ResolutionConfig;
import com.csh.framework.service.BaseService;

public interface ResolutionConfigService extends BaseService<ResolutionConfig, Long> {

  /**
   * 根据分辨率获取app加载页广告最接近的分辨率配置
   * 
   * @param piWeight
   * @param piHeight
   * @return
   */
  ResolutionConfig getResolutionConfig(Integer piWight, Integer piHeight);
}
