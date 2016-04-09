package com.csh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.ProvinceDao;
import com.csh.entity.Province;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.ProvinceService;

@Service("provinceServiceImpl")
public class ProvinceServiceImpl extends BaseServiceImpl<Province, Long> implements ProvinceService {

  @Resource(name = "provinceDaoImpl")
  private ProvinceDao provinceDao;

  @Resource(name = "provinceDaoImpl")
  public void setBaseDao(ProvinceDao provinceDao) {
    super.setBaseDao(provinceDao);
  }

  @Override
  public Province getProvinceByShortName(String shortName) {
    return provinceDao.getProvinceByShortName(shortName);
  }
}
