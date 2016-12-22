package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.VehicleDtc;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.VehicleDtcDao;
@Repository("vehicleDtcDaoImpl")
public class VehicleDtcDaoImpl extends  BaseDaoImpl<VehicleDtc,Long> implements VehicleDtcDao {

}