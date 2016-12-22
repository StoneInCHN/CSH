package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.Province;
import com.csh.dao.ProvinceDao;
import com.csh.service.ProvinceService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("provinceServiceImpl")
public class ProvinceServiceImpl extends BaseServiceImpl<Province,Long> implements ProvinceService {

      @Resource(name="provinceDaoImpl")
      public void setBaseDao(ProvinceDao provinceDao) {
         super.setBaseDao(provinceDao);
  }
}