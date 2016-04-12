package com.csh.dao;

import com.csh.entity.ApkVersion;
import com.csh.framework.dao.BaseDao;

public interface APKVersionDao extends BaseDao<ApkVersion, Long> {
  boolean versionExists(String versionName, Long id);
}
