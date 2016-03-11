package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.Store;
import com.csh.dao.StoreDao;
import com.csh.service.StoreService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("storeServiceImpl")
public class StoreServiceImpl extends BaseServiceImpl<Store,Long> implements StoreService {

      @Resource(name="storeDaoImpl")
      public void setBaseDao(StoreDao storeDao) {
         super.setBaseDao(storeDao);
  }
}