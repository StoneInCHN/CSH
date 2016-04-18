package com.csh.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csh.beans.Message;
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
import com.csh.entity.MessageInfo;
import com.csh.entity.MsgEndUser;
import com.csh.entity.Vehicle;
import com.csh.entity.VehicleInsurance;
import com.csh.entity.Wallet;
import com.csh.entity.WalletRecord;
import com.csh.entity.commonenum.CommonEnum.BalanceType;
import com.csh.entity.commonenum.CommonEnum.ChargeStatus;
import com.csh.entity.commonenum.CommonEnum.MessageType;
import com.csh.entity.commonenum.CommonEnum.PaymentType;
import com.csh.entity.commonenum.CommonEnum.ReservationInfoFrom;
import com.csh.entity.commonenum.CommonEnum.WalletType;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.CarServiceRecordService;
import com.csh.service.MessageInfoService;
import com.csh.utils.SettingUtils;
import com.csh.utils.TimeUtils;
import com.csh.utils.ToolsUtils;
import com.csh.utils.UcpaasUtil;

@Service("carServiceRecordServiceImpl")
public class CarServiceRecordServiceImpl extends BaseServiceImpl<CarServiceRecord, Long> implements
    CarServiceRecordService {

  @Resource(name = "carServiceRecordDaoImpl")
  private CarServiceRecordDao carServiceRecordDao;

  @Resource(name = "walletDaoImpl")
  private WalletDao walletDao;

  @Resource(name = "messageInfoServiceImpl")
  private MessageInfoService messageInfoService;

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
    if (carService.getServiceCategory().getId().equals(setting.getServiceCateMaintain())) {
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

    } else if (carService.getServiceCategory().getId().equals(setting.getServiceCateBeautify())) {
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


  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public CarServiceRecord updatePayStatus(CarServiceRecord carServiceRecord) {
    Setting setting = SettingUtils.get();
    // 消费兑换积分.规则 1元=1积分
    Wallet wallet = carServiceRecord.getEndUser().getWallet();
    WalletRecord walletRecord = new WalletRecord();
    walletRecord.setWallet(wallet);
    walletRecord.setBalanceType(BalanceType.INCOME);
    walletRecord.setWalletType(WalletType.SCORE);
    walletRecord.setScore(carServiceRecord.getPrice());
    wallet.getWalletRecords().add(walletRecord);
    wallet.setScore(wallet.getScore().add(walletRecord.getScore()));
    walletDao.merge(wallet);

    if (setting.getServiceCateWash().equals(
        carServiceRecord.getCarService().getServiceCategory().getId())) {
      Integer tokenNo = (int) ((Math.random() * 9 + 1) * 100000);
      carServiceRecord.setPayCode(tokenNo.toString());
      EndUser endUser = carServiceRecord.getEndUser();
      MessageInfo msg = new MessageInfo();
      msg.setMessageType(MessageType.PERSONALMSG);
      String msgContent =
          Message.success("csh.buyService.reminder",
              TimeUtils.format("yyyy-MM-dd HH:mm:ss", carServiceRecord.getCreateDate().getTime()),
              carServiceRecord.getTenantName(), carServiceRecord.getCarService().getServiceName(),
              carServiceRecord.getCarService().getTenantInfo().getAddress(), tokenNo.toString())
              .getContent();
      msg.setMessageContent(msgContent);
      MsgEndUser msgEndUser = new MsgEndUser();
      msgEndUser.setEndUser(endUser);
      msgEndUser.setIsPush(false);
      msgEndUser.setIsRead(false);
      msgEndUser.setMessage(msg);
      msg.getMsgUser().add(msgEndUser);
      messageInfoService.save(msg);
      /**
       * jpush推送消息
       */
      messageInfoService.jpushMsg(msg);
      /**
       * 发送短信通知
       */
      String purDate =
          TimeUtils.format("yyyy-MM-dd HH:mm:ss", carServiceRecord.getCreateDate().getTime());
      UcpaasUtil.SendPurchaseInfoBySms(endUser.getUserName(), purDate,
          carServiceRecord.getTenantName(), carServiceRecord.getCarService().getServiceName(),
          carServiceRecord.getCarService().getTenantInfo().getAddress(), tokenNo.toString());

    }
    carServiceRecordDao.merge(carServiceRecord);
    return carServiceRecord;
  }
}
