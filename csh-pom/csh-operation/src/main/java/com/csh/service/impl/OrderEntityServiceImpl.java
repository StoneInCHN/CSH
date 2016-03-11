package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.OrderEntity;
import com.csh.dao.OrderEntityDao;
import com.csh.service.OrderEntityService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("orderEntityServiceImpl")
public class OrderEntityServiceImpl extends BaseServiceImpl<OrderEntity,Long> implements OrderEntityService {

      @Resource(name="orderEntityDaoImpl")
      public void setBaseDao(OrderEntityDao orderEntityDao) {
         super.setBaseDao(orderEntityDao);
  }
}