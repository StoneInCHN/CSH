package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.estore.ShippingItem;
import com.csh.dao.ShippingItemDao;
import com.csh.service.ShippingItemService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("shippingItemServiceImpl")
public class ShippingItemServiceImpl extends BaseServiceImpl<ShippingItem,Long> implements ShippingItemService {

      @Resource(name="shippingItemDaoImpl")
      public void setBaseDao(ShippingItemDao shippingItemDao) {
         super.setBaseDao(shippingItemDao);
  }
}