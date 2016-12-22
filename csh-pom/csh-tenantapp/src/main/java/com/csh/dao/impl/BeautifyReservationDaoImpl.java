package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.BeautifyReservation;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.BeautifyReservationDao;
@Repository("beautifyReservationDaoImpl")
public class BeautifyReservationDaoImpl extends  BaseDaoImpl<BeautifyReservation,Long> implements BeautifyReservationDao {

}