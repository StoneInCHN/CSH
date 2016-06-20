package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.estore.CartItem;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.CartItemDao;
@Repository("cartItemDaoImpl")
public class CartItemDaoImpl extends  BaseDaoImpl<CartItem,Long> implements CartItemDao {

}