package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.VehicleOil;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.VehicleOilDao;
@Repository("vehicleOilDaoImpl")
public class VehicleOilDaoImpl extends  BaseDaoImpl<VehicleOil,Long> implements VehicleOilDao {

}