package com.csh.service.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csh.dao.CarServiceRecordDao;
import com.csh.dao.WalletDao;
import com.csh.entity.CarService;
import com.csh.entity.CarServiceRecord;
import com.csh.entity.EndUser;
import com.csh.entity.VehicleInsurance;
import com.csh.entity.Wallet;
import com.csh.entity.WalletRecord;
import com.csh.entity.commonenum.CommonEnum.BalanceType;
import com.csh.entity.commonenum.CommonEnum.ChargeStatus;
import com.csh.entity.commonenum.CommonEnum.PaymentType;
import com.csh.entity.commonenum.CommonEnum.WalletType;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.CarServiceRecordService;
import com.csh.utils.ToolsUtils;

@Service("carServiceRecordServiceImpl")
public class CarServiceRecordServiceImpl extends BaseServiceImpl<CarServiceRecord, Long> implements
    CarServiceRecordService {

  @Resource(name = "carServiceRecordDaoImpl")
  private CarServiceRecordDao carServiceRecordDao;

  @Resource(name = "walletDaoImpl")
  private WalletDao walletDao;

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
      ChargeStatus chargeStatus, BigDecimal price, PaymentType paymentType) {
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
}
