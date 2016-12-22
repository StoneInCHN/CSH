package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.CarService;
import com.csh.dao.CarServiceDao;
import com.csh.service.CarServiceService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("carServiceServiceImpl")
public class CarServiceServiceImpl extends BaseServiceImpl<CarService,Long> implements CarServiceService {

      @Resource(name="carServiceDaoImpl")
      public void setBaseDao(CarServiceDao carServiceDao) {
         super.setBaseDao(carServiceDao);
  }
}