package com.csh.dao.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.csh.dao.VendorDao;
import com.csh.entity.Vendor;
import com.csh.framework.dao.impl.BaseDaoImpl;

@Repository("vendorDaoImpl")
public class VendorDaoImpl extends BaseDaoImpl<Vendor, Long> implements VendorDao{

  @Override
  @Transactional
  @Cacheable(value="vendor",key="'vendor.id='+#id")
  public Vendor find(Long id) {
    return super.find(id);
  }

  @Override
  @Transactional
  @CachePut(value="vendor",key="'vendor.id='+#id")
  public Vendor merge(Vendor entity) {
    return super.merge(entity);
  }

  @Override
  @Transactional
  @CacheEvict(value="vendor",key="'vendor.id='+#id")
  public void remove(Vendor entity) {
    super.remove(entity);
  }
  
}
