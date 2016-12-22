package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.CarServiceItem;
import com.csh.dao.CarServiceItemDao;
import com.csh.service.CarServiceItemService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("carServiceItemServiceImpl")
public class CarServiceItemServiceImpl extends BaseServiceImpl<CarServiceItem,Long> implements CarServiceItemService {

      @Resource(name="carServiceItemDaoImpl")
      public void setBaseDao(CarServiceItemDao carServiceItemDao) {
         super.setBaseDao(carServiceItemDao);
  }
}