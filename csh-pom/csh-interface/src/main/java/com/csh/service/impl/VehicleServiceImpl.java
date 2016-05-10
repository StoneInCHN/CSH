package com.csh.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csh.dao.ReportDeviceBindStatisticsDao;
import com.csh.dao.VehicleDao;
import com.csh.entity.DeviceInfo;
import com.csh.entity.ReportDeviceBindStatistics;
import com.csh.entity.Vehicle;
import com.csh.entity.commonenum.CommonEnum.BindStatus;
import com.csh.entity.commonenum.CommonEnum.CouponSendType;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.CouponService;
import com.csh.service.VehicleService;
import com.csh.utils.TimeUtils;

@Service("vehicleServiceImpl")
public class VehicleServiceImpl extends BaseServiceImpl<Vehicle, Long> implements VehicleService {

  @Resource(name = "vehicleDaoImpl")
  private VehicleDao vehicleDao;

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

    ReportDeviceBindStatistics report =
        reportDeviceBindStatisticsDao.getReportByDate(TimeUtils.formatDate2Day(new Date()));
    if (report != null) {
      report.setBindDeviceNum(report.getBindDeviceNum() + 1);
      reportDeviceBindStatisticsDao.merge(report);
    } else {
      report = new ReportDeviceBindStatistics();
      report.setStatisticsDate(new Date());
      report.setBindDeviceNum(1);
      reportDeviceBindStatisticsDao.persist(report);
    }

    couponService.takeCouponBySendType(deviceInfo.getTenantID(), vehicle.getEndUser(), CouponSendType.BIND);
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
}
