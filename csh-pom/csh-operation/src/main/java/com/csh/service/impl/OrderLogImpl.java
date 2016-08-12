package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.estore.OrderLog;
import com.csh.dao.OrderLogDao;
import com.csh.service.OrderLogService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("orderLogServiceImpl")
public class OrderLogImpl extends BaseServiceImpl<OrderLog,Long> implements OrderLogService {

      @Resource(name="orderLogDaoImpl")
      public void setBaseDao(OrderLogDao orderLogDao) {
         super.setBaseDao(orderLogDao);
  }
}