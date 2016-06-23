package com.csh.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;



import com.csh.dao.APKVersionDao;
import com.csh.entity.ApkVersion;
import com.csh.entity.estore.Order;
import com.csh.framework.ordering.Ordering;
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

	@Override
	public ApkVersion getTheLastVersion() {
		ApkVersion apkVersion = null;
		List<Ordering> orders = new ArrayList<Ordering>();
		orders.add(Ordering.desc("versionCode"));
		List<ApkVersion> apkVersions = apkVersionDao.findList(null, 1, null, orders);
		if (apkVersions!=null && apkVersions.size() >0) {
			apkVersion = apkVersions.get(0);
		}
		return apkVersion;
	}

}
