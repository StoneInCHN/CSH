package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.Position;
import com.csh.dao.PositionDao;
import com.csh.service.PositionService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("positionServiceImpl")
public class PositionServiceImpl extends BaseServiceImpl<Position,Long> implements PositionService {

      @Resource(name="positionDaoImpl")
      public void setBaseDao(PositionDao positionDao) {
         super.setBaseDao(positionDao);
  }
}