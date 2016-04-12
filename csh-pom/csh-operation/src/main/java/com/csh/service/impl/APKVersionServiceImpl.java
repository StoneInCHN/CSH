package com.csh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.APKVersionDao;
import com.csh.entity.ApkVersion;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.APKVersionService;

@Service("apkVersionServiceImpl")
public class APKVersionServiceImpl extends BaseServiceImpl<ApkVersion, Long> implements
    APKVersionService {

  @Resource(name = "apkVersionDaoImpl")
  private APKVersionDao apkVersionDao;

  @Resource(name = "apkVersionDaoImpl")
  public void setBaseDao(APKVersionDao apkVersionDao) {
    super.setBaseDao(apkVersionDao);
  }

  @Override
  public boolean versionExists(String versionName, Long id) {
    return apkVersionDao.versionExists(versionName, id);
  }

}
