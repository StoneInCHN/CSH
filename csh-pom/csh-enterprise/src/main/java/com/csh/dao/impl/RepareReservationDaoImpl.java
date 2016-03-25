package com.csh.dao.impl; 

import java.util.List;
import java.util.Map;

import javax.persistence.FlushModeType;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.csh.dao.RepareReservationDao;
import com.csh.entity.Department;
import com.csh.entity.RepareReservation;
import com.csh.framework.dao.impl.BaseDaoImpl;
@Repository("repareReservationDaoImpl")
public class RepareReservationDaoImpl extends  BaseDaoImpl<RepareReservation,Long> implements RepareReservationDao {

}