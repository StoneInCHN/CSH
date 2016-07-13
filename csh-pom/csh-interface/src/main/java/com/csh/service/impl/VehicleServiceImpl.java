package com.csh.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csh.dao.AdvanceDepositsDao;
import com.csh.dao.ReportDeviceBindStatisticsDao;
import com.csh.dao.VehicleDao;
import com.csh.entity.AdvanceDeposits;
import com.csh.entity.DeviceInfo;
import com.csh.entity.EndUser;
import com.csh.entity.Vehicle;
import com.csh.entity.commonenum.CommonEnum.AdvanceUsageType;
import com.csh.entity.commonenum.CommonEnum.BindStatus;
import com.csh.entity.commonenum.CommonEnum.CouponSendType;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.CouponService;
import com.csh.service.VehicleService;

@Service("vehicleServiceImpl")
public class VehicleServiceImpl extends BaseServiceImpl<Vehicle, Long> implements VehicleService {

  @Resource(name = "vehicleDaoImpl")
  private VehicleDao vehicleDao;

  @Resource(name = "advanceDepositsDaoImpl")
  private AdvanceDepositsDao advanceDepositsDao;

  @Resource(name = "couponServiceImpl")
  private CouponService couponService;

  @Resource(name = "reportDeviceBindStatisticsDaoImpl")
  private ReportDeviceBindStatisticsDao reportDeviceBindStatisticsDao;

  @Resource(name = "vehicleDaoImpl")
  public void setBaseDao(VehicleDao vehicleDao) {
    super.setBaseDao(vehicleDao);
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public Vehicle bindDevice(Vehicle vehicle, DeviceInfo deviceInfo) {

    vehicle.setDeviceNo(deviceInfo.getDeviceNo());
    deviceInfo.setVehicle(vehicle);
    deviceInfo.setBindTime(new Date());
    deviceInfo.setBindStatus(BindStatus.BINDED);
    vehicle.setDevice(deviceInfo);
    vehicle.setTenantID(deviceInfo.getTenantID());
    vehicleDao.merge(vehicle);

    List<Filter> filters = new ArrayList<Filter>();
    Filter tenantFilter = new Filter("tenantID", Operator.eq, deviceInfo.getTenantID());
    Filter endUserFilter = new Filter("endUser", Operator.eq, vehicle.getEndUser());
    Filter deviceFilter = new Filter("deviceNo", Operator.eq, deviceInfo.getDeviceNo());
    Filter typeFilter = new Filter("usageType", Operator.eq, AdvanceUsageType.DEVICE);
    Filter bindFilter = new Filter("isBind", Operator.eq, false);
    filters.add(bindFilter);
    filters.add(typeFilter);
    filters.add(tenantFilter);
    filters.add(endUserFilter);
    filters.add(deviceFilter);
    List<AdvanceDeposits> advanceDeposits = advanceDepositsDao.findList(null, null, filters, null);
    for (AdvanceDeposits deposit : advanceDeposits) {
      deposit.setIsBind(true);
      advanceDepositsDao.merge(deposit);
    }
    // ReportDeviceBindStatistics report =
    // reportDeviceBindStatisticsDao.getReportByDate(TimeUtils.formatDate2Day(new Date()));
    // if (report != null) {
    // report.setBindDeviceNum(report.getBindDeviceNum() + 1);
    // reportDeviceBindStatisticsDao.merge(report);
    // } else {
    // report = new ReportDeviceBindStatistics();
    // report.setStatisticsDate(new Date());
    // report.setBindDeviceNum(1);
    // reportDeviceBindStatisticsDao.persist(report);
    // }

    Boolean flag =
        couponService.takeCouponBySendType(null, vehicle.getEndUser(), CouponSendType.DEVICEBIND);
    vehicle.setIsGetCoupon(flag);
    return vehicle;
  }

  @Override
  public Vehicle getVehicleByPlate(String plate) {
    return vehicleDao.getVehicleByPlate(plate);
  }

  @Override
  public Vehicle getVehicleByVehicleNo(String vehicleNo) {
    return vehicleDao.getVehicleByVehicleNo(vehicleNo);
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public Vehicle bindTenant(Vehicle vehicle) {
    vehicleDao.merge(vehicle);
    Boolean flag =
        couponService.takeCouponBySendType(vehicle.getTenantID(), vehicle.getEndUser(),
            CouponSendType.TENANTBIND);
    vehicle.setIsGetCoupon(flag);
    return vehicle;
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public Vehicle addVehicle(Vehicle vehicle, EndUser endUser, Boolean isDefault) {
    if (endUser.getVehicles() == null || endUser.getVehicles().size() <= 0) {
      vehicle.setIsDefault(true);
    } else {
      vehicle.setIsDefault(isDefault);
      if (isDefault) {
        for (Vehicle vehi : endUser.getVehicles()) {
          if (vehi.getIsDefault()) {
            vehi.setIsDefault(false);
            vehicleDao.merge(vehi);
          }
        }
      }
    }
    vehicle.setEndUser(endUser);
    vehicleDao.persist(vehicle);
    return vehicle;
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public Vehicle updateVehicle(Vehicle vehicle, EndUser endUser, Boolean isDefault) {
    vehicle.setIsDefault(isDefault);
    if (isDefault) {
      for (Vehicle vehi : endUser.getVehicles()) {
        if (!vehi.getId().equals(vehicle.getId()) && vehi.getIsDefault()) {
          vehi.setIsDefault(false);
          vehicleDao.merge(vehi);
        }
      }
    }
    vehicleDao.merge(vehicle);
    return vehicle;
  }
}
