package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.Distributor;
import com.csh.dao.VendorDao;
import com.csh.service.VendorService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("vendorServiceImpl")
public class VendorServiceImpl extends BaseServiceImpl<Distributor,Long> implements VendorService {

      @Resource(name="vendorDaoImpl")
      public void setBaseDao(VendorDao vendorDao) {
         super.setBaseDao(vendorDao);
  }
}