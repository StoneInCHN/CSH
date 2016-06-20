package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.estore.Cart;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.CartDao;
@Repository("cartDaoImpl")
public class CartDaoImpl extends  BaseDaoImpl<Cart,Long> implements CartDao {

}