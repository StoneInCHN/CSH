package com.csh.dao.impl; 

import org.springframework.stereotype.Repository;

import com.csh.dao.BeautifyReservationDao;
import com.csh.entity.BeautifyReservation;
import com.csh.framework.dao.impl.BaseDaoImpl;
@Repository("beautifyReservationDaoImpl")
public class BeautifyReservationDaoImpl extends  BaseDaoImpl<BeautifyReservation,Long> implements BeautifyReservationDao {

}