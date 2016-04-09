package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.VehicleInsurance;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.VehicleInsuranceDao;
@Repository("vehicleInsuranceDaoImpl")
public class VehicleInsuranceDaoImpl extends  BaseDaoImpl<VehicleInsurance,Long> implements VehicleInsuranceDao {

}