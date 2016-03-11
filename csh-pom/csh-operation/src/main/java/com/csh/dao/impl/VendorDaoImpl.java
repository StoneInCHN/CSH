package com.csh.dao.impl;

import org.springframework.stereotype.Repository;

import com.csh.dao.VendorDao;
import com.csh.entity.Vendor;
import com.csh.framework.dao.impl.BaseDaoImpl;

@Repository("vendorDaoImpl")
public class VendorDaoImpl extends BaseDaoImpl<Vendor, Long> implements VendorDao {

}
