package com.csh.service.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csh.beans.Setting;
import com.csh.dao.BeautifyReservationDao;
import com.csh.dao.CarServiceRecordDao;
import com.csh.dao.MaintainReservationDao;
import com.csh.dao.WalletDao;
import com.csh.entity.BeautifyReservation;
import com.csh.entity.CarService;
import com.csh.entity.CarServiceRecord;
import com.csh.entity.EndUser;
import com.csh.entity.MaintainReservation;
import com.csh.entity.Vehicle;
import com.csh.entity.VehicleInsurance;
import com.csh.entity.Wallet;
import com.csh.entity.WalletRecord;
import com.csh.entity.commonenum.CommonEnum.BalanceType;
import com.csh.entity.commonenum.CommonEnum.ChargeStatus;
import com.csh.entity.commonenum.CommonEnum.PaymentType;
import com.csh.entity.commonenum.CommonEnum.ReservationInfoFrom;
import com.csh.entity.commonenum.CommonEnum.WalletType;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.CarServiceRecordService;
import com.csh.utils.SettingUtils;
import com.csh.utils.ToolsUtils;

@Service("carServiceRecordServiceImpl")
public class CarServiceRecordServiceImpl extends BaseServiceImpl<CarServiceRecord, Long> implements
    CarServiceRecordService {

  @Resource(name = "carServiceRecordDaoImpl")
  private CarServiceRecordDao carServiceRecordDao;

  @Resource(name = "walletDaoImpl")
  private WalletDao walletDao;

  @Resource(name = "maintainReservationDaoImpl")
  private MaintainReservationDao maintainReservationDao;

  @Resource(name = "beautifyReservationDaoImpl")
  private BeautifyReservationDao beautifyReservationDao;

  @Resource(name = "carServiceRecordDaoImpl")
  public void setBaseDao(CarServiceRecordDao carServiceRecordDao) {
    super.setBaseDao(carServiceRecordDao);
  }

  @Override
  public Page<CarServiceRecord> getBuyRecordListByUserAndCategory(Long userId, Long categoryId,
      Pageable pageable) {
    return carServiceRecordDao.getBuyRecordListByUserAndCategory(userId, categoryId, pageable);
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public CarServiceRecord createServiceRecord(EndUser endUser, CarService carService,
      ChargeStatus chargeStatus, BigDecimal price, PaymentType paymentType, Date subscribeDate) {
    Setting setting = SettingUtils.get();
    CarServiceRecord carServiceRecord = new CarServiceRecord();
    carServiceRecord.setTenantID(carService.getTenantInfo().getId());
    String recordNo = ToolsUtils.generateRecordNo(carService.getTenantInfo().getOrgCode());
    carServiceRecord.setRecordNo(recordNo);
    carServiceRecord.setCarService(carService);
    carServiceRecord.setEndUser(endUser);
    carServiceRecord.setChargeStatus(chargeStatus);
    carServiceRecord.setPrice(price);
    carServiceRecord.setTenantName(carService.getTenantInfo().getTenantName());
    carServiceRecord.setTenantPhoto(carService.getTenantInfo().getPhoto());
    carServiceRecord.setSubscribeDate(subscribeDate);
    if (carService.getServiceCategory().getId() == setting.getServiceCateMaintain()) {
      MaintainReservation maintainReservation = new MaintainReservation();
      maintainReservation.setCarServiceRecord(carServiceRecord);
      maintainReservation.setEndUser(endUser);
      Vehicle vehicle = endUser.getDefaultVehicle();
      maintainReservation.setPlate(vehicle.getPlate());
      maintainReservation.setVehicleBrand(vehicle.getVehicleFullBrand());
      maintainReservation.setReservationDate(subscribeDate);
      maintainReservation.setReservationInfoFrom(ReservationInfoFrom.APP);
      maintainReservation.setTenantID(carService.getTenantInfo().getId());
      carServiceRecord.setMaintainReservation(maintainReservation);
      maintainReservationDao.persist(maintainReservation);

    } else if (carService.getServiceCategory().getId() == setting.getServiceCateMaintain()) {
      BeautifyReservation beautifyReservation = new BeautifyReservation();
      beautifyReservation.setCarServiceRecord(carServiceRecord);
      beautifyReservation.setEndUser(endUser);
      Vehicle vehicle = endUser.getDefaultVehicle();
      beautifyReservation.setPlate(vehicle.getPlate());
      beautifyReservation.setVehicleBrand(vehicle.getVehicleFullBrand());
      beautifyReservation.setReservationDate(subscribeDate);
      beautifyReservation.setReservationInfoFrom(ReservationInfoFrom.APP);
      beautifyReservation.setTenantID(carService.getTenantInfo().getId());
      carServiceRecord.setBeautifyReservation(beautifyReservation);
      beautifyReservationDao.persist(beautifyReservation);
    }
    carServiceRecordDao.persist(carServiceRecord);
    if (PaymentType.WALLET.equals(paymentType)) {
      Wallet wallet = endUser.getWallet();
      wallet.setBalanceAmount(wallet.getBalanceAmount().subtract(price));
      WalletRecord walletRecord = new WalletRecord();
      walletRecord.setBalanceType(BalanceType.OUTCOME);
      walletRecord.setMoney(price);
      walletRecord.setWallet(wallet);
      walletRecord.setWalletType(WalletType.MONEY);
      wallet.getWalletRecords().add(walletRecord);
      walletDao.merge(wallet);

    }
    return carServiceRecord;

  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public CarServiceRecord createInsuranceRecord(EndUser endUser, CarService carService,
      BigDecimal price, String company, String IDphoto, String drivingLicensePhoto,
      String driverLicensePhoto, Boolean isOwned, Boolean isLoaned) {

    CarServiceRecord carServiceRecord = new CarServiceRecord();
    // carServiceRecord.setTenantID(carService.getTenantInfo().getId());
    carServiceRecord.setTenantID(endUser.getDefaultVehicle().getDevice().getTenantID());
    String recordNo = ToolsUtils.generateRecordNo(carService.getTenantInfo().getOrgCode());
    carServiceRecord.setRecordNo(recordNo);
    carServiceRecord.setCarService(carService);
    carServiceRecord.setEndUser(endUser);
    carServiceRecord.setPrice(price);
    carServiceRecord.setTenantName(carService.getTenantInfo().getTenantName());
    VehicleInsurance vehicleInsurance = new VehicleInsurance();
    vehicleInsurance.setInsuredCompany(company);
    vehicleInsurance.setIDphoto(IDphoto);
    vehicleInsurance.setDrivingLicensePhoto(drivingLicensePhoto);
    vehicleInsurance.setDriverLicensePhoto(driverLicensePhoto);
    vehicleInsurance.setIsLoaned(isLoaned);
    vehicleInsurance.setIsOwned(isOwned);
    carServiceRecord.setVehicleInsurance(vehicleInsurance);
    carServiceRecordDao.persist(carServiceRecord);
    return carServiceRecord;
  }

  @Override
  public Boolean existRecordByUser(EndUser user, CarService carService, ChargeStatus chargeStatus) {
    List<Filter> filters = new ArrayList<Filter>();
    Filter userFilter = new Filter("endUser", Operator.eq, user);
    Filter serviceFilter = new Filter("carService", Operator.eq, carService);
    Filter statusFilter = new Filter("chargeStatus", Operator.eq, chargeStatus);
    filters.add(userFilter);
    filters.add(serviceFilter);
    filters.add(statusFilter);
    List<CarServiceRecord> records = carServiceRecordDao.findList(null, null, filters, null);
    if (records != null && records.size() > 0) {
      return true;
    }
    return false;
  }
}
