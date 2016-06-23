package com.csh.service;

import com.csh.entity.ApkVersion;
import com.csh.framework.service.BaseService;

public interface APKVersionService extends BaseService<ApkVersion, Long> {

  boolean versionExists(String versionName,Long id);
  
  /**
   * 获取最新的apk版本
   * @return
   */
  ApkVersion getTheLastVersion();
  
}
