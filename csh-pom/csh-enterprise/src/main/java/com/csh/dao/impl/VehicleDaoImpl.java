package com.csh.dao.impl; 

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.csh.dao.VehicleDao;
import com.csh.entity.Vehicle;
import com.csh.framework.dao.impl.BaseDaoImpl;
@Repository("vehicleDaoImpl")
public class VehicleDaoImpl extends  BaseDaoImpl<Vehicle,Long> implements VehicleDao {

  @Override
  public Vehicle findVehicleByDeviceId (Long deviceId)
  {
    String jpql =
        "select vehicle from Vehicle vehicle, DeviceInfo deviceInfo"
            + " where deviceInfo.vehicle = vehicle and deviceInfo.deviceNo = :deviceNo";
    TypedQuery<Vehicle> query =
        entityManager.createQuery(jpql, Vehicle.class).setParameter("deviceNo", deviceId.toString ()).setFlushMode(FlushModeType.COMMIT);
    List<Vehicle> vehicleList = query.getResultList ();
    
    if (vehicleList != null && vehicleList.size () ==1)
    {
      return vehicleList.get (0);
    }
    return null;
  }

}