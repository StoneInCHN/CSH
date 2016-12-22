package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.BeautifyReservation;
import com.csh.dao.BeautifyReservationDao;
import com.csh.service.BeautifyReservationService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("beautifyReservationServiceImpl")
public class BeautifyReservationServiceImpl extends BaseServiceImpl<BeautifyReservation,Long> implements BeautifyReservationService {

      @Resource(name="beautifyReservationDaoImpl")
      public void setBaseDao(BeautifyReservationDao beautifyReservationDao) {
         super.setBaseDao(beautifyReservationDao);
  }
}