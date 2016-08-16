package com.csh.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.BooleanUtils;
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
import com.csh.entity.commonenum.CommonEnum.SystemConfigKey;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.CouponService;
import com.csh.service.VehicleService;
import com.csh.service.WalletService;

@Service("vehicleServiceImpl")
public class VehicleServiceImpl extends BaseServiceImpl<Vehicle, Long> implements VehicleService {

  @Resource(name = "vehicleDaoImpl")
  private VehicleDao vehicleDao;

  @Resource(name = "advanceDepositsDaoImpl")
  private AdvanceDepositsDao advanceDepositsDao;

  @Resource(name = "couponServiceImpl")
  private CouponService couponService;

  @Resource(name = "walletServiceImpl")
  private WalletService walletService;

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
    /**
     * 车辆第一次绑定设备送基金红包
     */
    if (vehicle.getIsFirstBindDevice()) {
      walletService.giftRedPacket(vehicle.getEndUser().getWallet(),
          SystemConfigKey.GROUTHFUND_BIND, "csh.wallet.bindDevice.comein.redPacket");
      vehicle.setIsFirstBindDevice(false);
    }
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
    /**
     * 第一次扫码关注租户送基金红包
     */
    if (vehicle.getIsFirstBindTenant()) {
      walletService.giftRedPacket(vehicle.getEndUser().getWallet(),
          SystemConfigKey.GROUTHFUND_ATTENTION, "csh.wallet.bindTenant.comein.redPacket");
      vehicle.setIsFirstBindTenant(false);
    }
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
      /**
       * 首次添加车辆送基金红包
       */
      walletService.giftRedPacket(endUser.getWallet(), SystemConfigKey.GROUTHFUND_ADDCAR,
          "csh.wallet.addVehicle.comein.redPacket");
    } else {
      if (isDefault != null) {
        vehicle.setIsDefault(isDefault);
        if (isDefault) {
          for (Vehicle vehi : endUser.getVehicles()) {
            if (BooleanUtils.isTrue(vehi.getIsDefault())) {
              vehi.setIsDefault(false);
              vehicleDao.merge(vehi);
            }
          }
        }
      } else {
        vehicle.setIsDefault(false);
      }

    }
    vehicle.setEndUser(endUser);
    vehicleDao.persist(vehicle);
    return vehicle;
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public Vehicle updateVehicle(Vehicle vehicle, EndUser endUser, Boolean isDefault) {
    if (isDefault != null) {
      vehicle.setIsDefault(isDefault);
      if (isDefault) {
        for (Vehicle vehi : endUser.getVehicles()) {
          if (!vehi.getId().equals(vehicle.getId()) && vehi.getIsDefault()) {
            vehi.setIsDefault(false);
            vehicleDao.merge(vehi);
          }
        }
      }
    }
    vehicleDao.merge(vehicle);
    return vehicle;
  }
}
