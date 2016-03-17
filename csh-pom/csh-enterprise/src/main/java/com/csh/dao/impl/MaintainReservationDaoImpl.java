package com.csh.dao.impl; 

import org.springframework.stereotype.Repository;

import com.csh.dao.MaintainReservationDao;
import com.csh.entity.MaintainReservation;
import com.csh.framework.dao.impl.BaseDaoImpl;
@Repository("maintainReservationDaoImpl")
public class MaintainReservationDaoImpl extends  BaseDaoImpl<MaintainReservation,Long> implements MaintainReservationDao {

}