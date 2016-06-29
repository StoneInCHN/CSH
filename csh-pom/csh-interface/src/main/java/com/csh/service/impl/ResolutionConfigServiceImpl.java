package com.csh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.ResolutionConfigDao;
import com.csh.entity.ResolutionConfig;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.ResolutionConfigService;

@Service("resolutionConfigServiceImpl")
public class ResolutionConfigServiceImpl extends BaseServiceImpl<ResolutionConfig, Long> implements
    ResolutionConfigService {

  @Resource(name = "resolutionConfigDaoImpl")
  private ResolutionConfigDao resolutionConfigDao;

  @Resource(name = "resolutionConfigDaoImpl")
  public void setBaseDao(ResolutionConfigDao resolutionConfigDao) {
    super.setBaseDao(resolutionConfigDao);
  }

  @Override
  public ResolutionConfig getResolutionConfig(Integer piWidth, Integer piHeight) {
    return resolutionConfigDao.getResolutionConfig(piWidth, piHeight);
  }
}
