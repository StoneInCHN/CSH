package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.estore.ShippingItem;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.ShippingItemDao;
@Repository("shippingItemDaoImpl")
public class ShippingItemDaoImpl extends  BaseDaoImpl<ShippingItem,Long> implements ShippingItemDao {

}