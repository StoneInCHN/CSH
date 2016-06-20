package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.estore.Shipping;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.ShippingDao;
@Repository("shippingDaoImpl")
public class ShippingDaoImpl extends  BaseDaoImpl<Shipping,Long> implements ShippingDao {

}