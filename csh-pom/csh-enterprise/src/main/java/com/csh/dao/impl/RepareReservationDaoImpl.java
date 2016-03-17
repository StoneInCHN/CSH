package com.csh.dao.impl; 

import org.springframework.stereotype.Repository;

import com.csh.dao.RepareReservationDao;
import com.csh.entity.RepareReservation;
import com.csh.framework.dao.impl.BaseDaoImpl;
@Repository("repareReservationDaoImpl")
public class RepareReservationDaoImpl extends  BaseDaoImpl<RepareReservation,Long> implements RepareReservationDao {

}