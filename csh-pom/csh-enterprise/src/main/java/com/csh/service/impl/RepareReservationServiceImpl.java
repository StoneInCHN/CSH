package com.csh.service.impl; 

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.RepareReservationDao;
import com.csh.entity.RepareReservation;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.RepareReservationService;

@Service("repareReservationServiceImpl")
public class RepareReservationServiceImpl extends BaseServiceImpl<RepareReservation,Long> implements RepareReservationService {

      @Resource(name="repareReservationDaoImpl")
      public void setBaseDao(RepareReservationDao repareReservationDao) {
         super.setBaseDao(repareReservationDao);
  }
}