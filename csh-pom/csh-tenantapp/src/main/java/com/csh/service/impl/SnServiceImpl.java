package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.Sn;
import com.csh.dao.SnDao;
import com.csh.service.SnService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("snServiceImpl")
public class SnServiceImpl extends BaseServiceImpl<Sn,Long> implements SnService {

      @Resource(name="snDaoImpl")
      public void setBaseDao(SnDao snDao) {
         super.setBaseDao(snDao);
  }
}