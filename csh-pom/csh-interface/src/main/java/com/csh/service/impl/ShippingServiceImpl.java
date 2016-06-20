package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.estore.Shipping;
import com.csh.dao.ShippingDao;
import com.csh.service.ShippingService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("shippingServiceImpl")
public class ShippingServiceImpl extends BaseServiceImpl<Shipping,Long> implements ShippingService {

      @Resource(name="shippingDaoImpl")
      public void setBaseDao(ShippingDao shippingDao) {
         super.setBaseDao(shippingDao);
  }
}