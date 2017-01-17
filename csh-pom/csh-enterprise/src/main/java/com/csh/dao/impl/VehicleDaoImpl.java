package com.csh.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.csh.dao.VehicleDao;
import com.csh.entity.Vehicle;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;

@Repository("vehicleDaoImpl")
public class VehicleDaoImpl extends BaseDaoImpl<Vehicle, Long> implements VehicleDao {

  @Override
  public Vehicle findVehicleByDeviceId(Long deviceId) {
    String jpql =
        "select vehicle from Vehicle vehicle, DeviceInfo deviceInfo"
            + " where deviceInfo.vehicle = vehicle and deviceInfo.deviceNo = :deviceNo";
    TypedQuery<Vehicle> query =
        entityManager.createQuery(jpql, Vehicle.class)
            .setParameter("deviceNo", deviceId.toString()).setFlushMode(FlushModeType.COMMIT);
    List<Vehicle> vehicleList = query.getResultList();

    if (vehicleList != null && vehicleList.size() == 1) {
      return vehicleList.get(0);
    }
    return null;
  }

  @Override
  public Page<Vehicle> listUnBuindVehicle(Long tenantId, String vehiclePlateSearch,
      String motorcadeSearch, String vehicleFullBrandSearch, Pageable pageable) {

    Map<String, Object> paramMap = new HashMap<String, Object>();

    String jpql =
        "select vehicle from DeviceInfo deviceInfo right join  deviceInfo.vehicle vehicle"
            + " where vehicle.plate != '0000000' and vehicle.tenantID=:tenantID and deviceInfo.id is null";
    if (vehiclePlateSearch != null) {
      jpql = jpql + " and vehicle.plate like :plate";
      paramMap.put("plate", "%" + vehiclePlateSearch + "%");
    }
    paramMap.put("tenantID", tenantId);
    return findPageCustomized(pageable, jpql, paramMap);

  }

  @Override
  public List<Vehicle> listVehicleBindDeviceByTenant(Long tenantID) {
    if (tenantID == null) {
      return null;
    }

    Map<String, Object> paramMap = new HashMap<String, Object>();
    String jpql =
        "select vehicle from DeviceInfo deviceInfo right join deviceInfo.vehicle vehicle"
            + " where vehicle.plate != '0000000' and vehicle.delFlag = false and vehicle.tenantID=:tenantID and deviceInfo.id is not null";
    paramMap.put("tenantID", tenantID);
    return findListCustomized(jpql, paramMap);
  }

	@Override
	public Page<Vehicle> listVehicleBindDeviceByTenant(Pageable pageable,Long tenantID, String plate) {
		
		  if (tenantID == null) {
		      return null;
		    }
	
		    Map<String, Object> paramMap = new HashMap<String, Object>();
		    String jpql =
		        "select vehicle from DeviceInfo deviceInfo right join deviceInfo.vehicle vehicle"
		            + " where vehicle.plate != '0000000' and vehicle.delFlag = false and vehicle.tenantID=:tenantID and deviceInfo.id is not null";
		    if (plate != null) {
				jpql = jpql+" and vehicle.plate like :plate";
				paramMap.put("plate", "%"+plate+"%");
			}
		    paramMap.put("tenantID", tenantID);
		    return findPageCustomized(pageable,jpql, paramMap);
	}
}
