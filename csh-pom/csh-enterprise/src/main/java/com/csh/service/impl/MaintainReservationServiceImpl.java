package com.csh.service.impl; 

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.MaintainReservationDao;
import com.csh.entity.MaintainReservation;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.MaintainReservationService;

@Service("maintainReservationServiceImpl")
public class MaintainReservationServiceImpl extends BaseServiceImpl<MaintainReservation,Long> implements MaintainReservationService {

      @Resource(name="maintainReservationDaoImpl")
      public void setBaseDao(MaintainReservationDao maintainReservationDao) {
         super.setBaseDao(maintainReservationDao);
  }
}