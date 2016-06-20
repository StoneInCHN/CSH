package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.estore.CartItem;
import com.csh.dao.CartItemDao;
import com.csh.service.CartItemService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("cartItemServiceImpl")
public class CartItemServiceImpl extends BaseServiceImpl<CartItem,Long> implements CartItemService {

      @Resource(name="cartItemDaoImpl")
      public void setBaseDao(CartItemDao cartItemDao) {
         super.setBaseDao(cartItemDao);
  }
}