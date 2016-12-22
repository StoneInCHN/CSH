package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.ResolutionConfig;
import com.csh.dao.ResolutionConfigDao;
import com.csh.service.ResolutionConfigService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("resolutionConfigServiceImpl")
public class ResolutionConfigServiceImpl extends BaseServiceImpl<ResolutionConfig,Long> implements ResolutionConfigService {

      @Resource(name="resolutionConfigDaoImpl")
      public void setBaseDao(ResolutionConfigDao resolutionConfigDao) {
         super.setBaseDao(resolutionConfigDao);
  }
}