package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.Distributor;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.VendorDao;
@Repository("vendorDaoImpl")
public class VendorDaoImpl extends  BaseDaoImpl<Distributor,Long> implements VendorDao {

}