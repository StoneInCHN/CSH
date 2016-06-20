package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.estore.ShippingMethod;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.ShippingMethodDao;
@Repository("shippingMethodDaoImpl")
public class ShippingMethodDaoImpl extends  BaseDaoImpl<ShippingMethod,Long> implements ShippingMethodDao {

}