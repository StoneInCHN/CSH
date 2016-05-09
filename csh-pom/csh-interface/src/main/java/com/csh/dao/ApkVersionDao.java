package com.csh.dao;

import java.util.List;

import com.csh.entity.ApkVersion;
import com.csh.framework.dao.BaseDao;

public interface ApkVersionDao extends BaseDao<ApkVersion, Long> {

  /**
   * 根据版本序列号获取最新版本apk
   * 
   * @param versionCode
   * @return
   */
  List<ApkVersion> getNewVersion(Integer versionCode);
}
