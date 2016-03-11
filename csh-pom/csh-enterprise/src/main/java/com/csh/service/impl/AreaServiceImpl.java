package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.Area;
import com.csh.dao.AreaDao;
import com.csh.service.AreaService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("areaServiceImpl")
public class AreaServiceImpl extends BaseServiceImpl<Area,Long> implements AreaService {

      @Resource(name="areaDaoImpl")
      public void setBaseDao(AreaDao areaDao) {
         super.setBaseDao(areaDao);
  }
}