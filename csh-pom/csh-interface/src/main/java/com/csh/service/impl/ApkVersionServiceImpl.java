package com.csh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.ApkVersionDao;
import com.csh.entity.ApkVersion;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.ApkVersionService;

@Service("apkVersionServiceImpl")
public class ApkVersionServiceImpl extends BaseServiceImpl<ApkVersion, Long> implements
    ApkVersionService {

  @Resource(name = "apkVersionDaoImpl")
  private ApkVersionDao apkVersionDao;

  @Resource(name = "apkVersionDaoImpl")
  public void setBaseDao(ApkVersionDao apkVersionDao) {
    super.setBaseDao(apkVersionDao);
  }

  @Override
  public ApkVersion getNewVersion(Integer versionCode) {
    return apkVersionDao.getNewVersion(versionCode);
  }
}
