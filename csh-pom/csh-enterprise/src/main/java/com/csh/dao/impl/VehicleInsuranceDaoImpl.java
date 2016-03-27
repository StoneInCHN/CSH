package com.csh.dao.impl; 

import org.springframework.stereotype.Repository;

import com.csh.dao.VehicleInsuranceDao;
import com.csh.entity.VehicleInsurance;
import com.csh.framework.dao.impl.BaseDaoImpl;
@Repository("vehicleInsuranceDaoImpl")
public class VehicleInsuranceDaoImpl extends  BaseDaoImpl<VehicleInsurance,Long> implements VehicleInsuranceDao {

}