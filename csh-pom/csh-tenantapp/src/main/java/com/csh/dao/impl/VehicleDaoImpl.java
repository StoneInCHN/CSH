package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.Vehicle;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.VehicleDao;
@Repository("vehicleDaoImpl")
public class VehicleDaoImpl extends  BaseDaoImpl<Vehicle,Long> implements VehicleDao {

}