package com.csh.service;

import com.csh.entity.ApkVersion;
import com.csh.framework.service.BaseService;

public interface ApkVersionService extends BaseService<ApkVersion, Long> {

  /**
   * 根据版本序列号获取最新版本apk
   * 
   * @param versionCode
   * @return
   */
  ApkVersion getNewVersion(Integer versionCode);
}
