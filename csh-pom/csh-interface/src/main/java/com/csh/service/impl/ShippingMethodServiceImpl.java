package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.estore.ShippingMethod;
import com.csh.dao.ShippingMethodDao;
import com.csh.service.ShippingMethodService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("shippingMethodServiceImpl")
public class ShippingMethodServiceImpl extends BaseServiceImpl<ShippingMethod,Long> implements ShippingMethodService {

      @Resource(name="shippingMethodDaoImpl")
      public void setBaseDao(ShippingMethodDao shippingMethodDao) {
         super.setBaseDao(shippingMethodDao);
  }
}