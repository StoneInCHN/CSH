package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.estore.Cart;
import com.csh.dao.CartDao;
import com.csh.service.CartService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("cartServiceImpl")
public class CartServiceImpl extends BaseServiceImpl<Cart,Long> implements CartService {

      @Resource(name="cartDaoImpl")
      public void setBaseDao(CartDao cartDao) {
         super.setBaseDao(cartDao);
  }
}