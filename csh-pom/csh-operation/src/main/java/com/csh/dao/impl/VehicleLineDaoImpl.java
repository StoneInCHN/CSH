package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.VehicleLine;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.VehicleLineDao;
@Repository("vehicleLineDaoImpl")
public class VehicleLineDaoImpl extends  BaseDaoImpl<VehicleLine,Long> implements VehicleLineDao {

}