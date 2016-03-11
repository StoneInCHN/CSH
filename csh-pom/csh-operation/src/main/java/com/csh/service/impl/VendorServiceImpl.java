package com.csh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.VendorDao;
import com.csh.entity.Vendor;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.VendorService;

@Service("vendorServiceImpl")
public class VendorServiceImpl extends BaseServiceImpl<Vendor, Long> implements VendorService{
  
  @Resource(name = "vendorDaoImpl")
  private VendorDao vendorDao;
  
  @Resource(name = "vendorDaoImpl")
  public void setBaseDao(VendorDao vendorDao) {
    super.setBaseDao(vendorDao);
  }
  
}
