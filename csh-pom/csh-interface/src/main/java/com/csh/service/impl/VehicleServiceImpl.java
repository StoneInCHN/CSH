package com.csh.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csh.dao.AdvanceDepositsDao;
import com.csh.dao.ReportDeviceBindStatisticsDao;
import com.csh.dao.VehicleDao;
import com.csh.entity.AdvanceDeposits;
import com.csh.entity.DeviceInfo;
import com.csh.entity.Vehicle;
import com.csh.entity.commonenum.CommonEnum.BindStatus;
import com.csh.entity.commonenum.CommonEnum.CouponSendType;
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
  public Vehicle bindDevice(Vehicle vehicle, DeviceInfo deviceInfo, BigDecimal bindPrice) {

    vehicle.setDeviceNo(deviceInfo.getDeviceNo());
    deviceInfo.setVehicle(vehicle);
    deviceInfo.setBindTime(new Date());
    deviceInfo.setBindStatus(BindStatus.BINDED);
    vehicle.setDevice(deviceInfo);
    vehicle.setTenantID(deviceInfo.getTenantID());
    vehicleDao.merge(vehicle);

    AdvanceDeposits advanceDeposits = vehicle.getEndUser().getAdvanceDeposits();
    BigDecimal amount = advanceDeposits.getBalanceAmount().subtract(bindPrice);
    advanceDeposits.setBalanceAmount(amount);
    advanceDepositsDao.merge(advanceDeposits);

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
}
