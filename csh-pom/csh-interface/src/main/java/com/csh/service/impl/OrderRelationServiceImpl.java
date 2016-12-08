package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.estore.OrderRelation;
import com.csh.dao.OrderRelationDao;
import com.csh.service.OrderRelationService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("orderRelationServiceImpl")
public class OrderRelationServiceImpl extends BaseServiceImpl<OrderRelation,Long> implements OrderRelationService {

      @Resource(name="orderRelationDaoImpl")
      public void setBaseDao(OrderRelationDao orderRelationDao) {
         super.setBaseDao(orderRelationDao);
  }
}