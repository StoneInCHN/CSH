package com.csh.service.impl; 

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.BeautifyReservationDao;
import com.csh.entity.BeautifyReservation;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.BeautifyReservationService;

@Service("beautifyReservationServiceImpl")
public class BeautifyReservationServiceImpl extends BaseServiceImpl<BeautifyReservation,Long> implements BeautifyReservationService {

      @Resource(name="beautifyReservationDaoImpl")
      BeautifyReservationDao beautifyReservationDao;
      @Resource
      public void setBaseDao(BeautifyReservationDao beautifyReservationDao) {
         super.setBaseDao(beautifyReservationDao);
  }

}