package com.csh.dao.impl;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.csh.dao.VehicleDao;
import com.csh.entity.Vehicle;
import com.csh.framework.dao.impl.BaseDaoImpl;

@Repository("vehicleDaoImpl")
public class VehicleDaoImpl extends BaseDaoImpl<Vehicle, Long> implements VehicleDao {

  @Override
  public Vehicle getVehicleByPlate(String plate) {
    if (plate == null) {
      return null;
    }
    try {
      String jpql = "select vehicle from Vehicle vehicle where vehicle.plate = :plate";
      return entityManager.createQuery(jpql, Vehicle.class).setFlushMode(FlushModeType.COMMIT)
          .setParameter("plate", plate).getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

}
