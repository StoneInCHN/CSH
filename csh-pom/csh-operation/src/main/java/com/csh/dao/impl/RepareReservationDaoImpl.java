package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.RepareReservation;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.RepareReservationDao;
@Repository("repareReservationDaoImpl")
public class RepareReservationDaoImpl extends  BaseDaoImpl<RepareReservation,Long> implements RepareReservationDao {

}