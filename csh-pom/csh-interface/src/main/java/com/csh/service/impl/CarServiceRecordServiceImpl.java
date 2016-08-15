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
import com.csh.dao.CarServiceDao;
import com.csh.dao.CarServiceRecordDao;
import com.csh.dao.CarServiceRecordPartInstDao;
import com.csh.dao.CarWashingCouponEndUserDao;
import com.csh.dao.CouponEndUserDao;
import com.csh.dao.ItemPartDao;
import com.csh.dao.MaintainReservationDao;
import com.csh.dao.WalletDao;
import com.csh.entity.AccountBalance;
import com.csh.entity.BeautifyReservation;
import com.csh.entity.CarService;
import com.csh.entity.CarServiceRecord;
import com.csh.entity.CarServiceRecordPartInst;
import com.csh.entity.CarWashingCouponEndUser;
import com.csh.entity.CouponEndUser;
import com.csh.entity.EndUser;
import com.csh.entity.ItemPart;
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
import com.csh.service.AccountBalanceService;
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

  @Resource(name = "couponEndUserDaoImpl")
  private CouponEndUserDao couponEndUserDao;

  @Resource(name = "messageInfoServiceImpl")
  private MessageInfoService messageInfoService;

  @Resource(name = "maintainReservationDaoImpl")
  private MaintainReservationDao maintainReservationDao;

  @Resource(name = "beautifyReservationDaoImpl")
  private BeautifyReservationDao beautifyReservationDao;

  @Resource(name = "itemPartDaoImpl")
  private ItemPartDao itemPartDao;

  @Resource(name = "carServiceRecordPartInstDaoImpl")
  private CarServiceRecordPartInstDao carServiceRecordPartInstDao;

  @Resource(name = "accountBalanceServiceImpl")
  private AccountBalanceService accountBalanceService;

  @Resource(name = "carWashingCouponEndUserDaoImpl")
  private CarWashingCouponEndUserDao carWashingCouponEndUserDao;

  @Resource(name = "carServiceDaoImpl")
  private CarServiceDao carServiceDao;


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
      ChargeStatus chargeStatus, BigDecimal price, PaymentType paymentType, Date subscribeDate,
      CouponEndUser couponEndUser, Long[] itemIds) {
    Setting setting = SettingUtils.get();
    CarServiceRecord carServiceRecord = new CarServiceRecord();
    carServiceRecord.setTenantID(carService.getTenantInfo().getId());
    String recordNo = ToolsUtils.generateRecordNo(carService.getTenantInfo().getOrgCode());
    carServiceRecord.setRecordNo(recordNo);
    carServiceRecord.setCarService(carService);
    carServiceRecord.setEndUser(endUser);
    carServiceRecord.setVehicle(endUser.getDefaultVehicle());
    carServiceRecord.setChargeStatus(chargeStatus);
    carServiceRecord.setPrice(price);
    carServiceRecord.setTenantName(carService.getTenantInfo().getTenantName());
    carServiceRecord.setTenantPhoto(carService.getTenantInfo().getPhoto());
    carServiceRecord.setSubscribeDate(subscribeDate);
    carServiceRecord.setPaymentType(paymentType);

    if (itemIds != null && itemIds.length > 0) {
      carServiceRecord.setPrice(new BigDecimal(0));
      for (Long itemId : itemIds) {
        ItemPart item = itemPartDao.find(itemId);
        CarServiceRecordPartInst inst = new CarServiceRecordPartInst();
        inst.setCarServiceRecord(carServiceRecord);
        inst.setPrice(item.getPrice());
        inst.setServiceItemPartDesc(item.getServiceItemPartDesc());
        inst.setServiceItemPartName(item.getServiceItemPartName());
        inst.setTenantID(item.getTenantID());
        BigDecimal instPrice = carServiceRecord.getPrice().add(inst.getPrice());
        carServiceRecord.setPrice(instPrice);
        carServiceRecord.getRecordItemPartInsts().add(inst);
      }
      price = carServiceRecord.getPrice();
    }


    /**
     * 优惠券支付
     */
    if (couponEndUser != null && !PaymentType.WASHCOUPON.equals(paymentType)) {
      List<Filter> filters = new ArrayList<Filter>();
      Filter filter = new Filter("couponEndUser", Operator.eq, couponEndUser);
      filters.add(filter);
      List<CarServiceRecord> list = carServiceRecordDao.findList(null, null, filters, null);
      for (CarServiceRecord record : list) {
        record.setCouponEndUser(null);
        record.setCouponSource(null);
        record.setDiscountPrice(null);
        carServiceRecordDao.merge(record);
      }
      carServiceRecord.setCouponEndUser(couponEndUser);
      BigDecimal discountPrice =
          carServiceRecord.getPrice().subtract(couponEndUser.getCoupon().getAmount());
      discountPrice =
          discountPrice.compareTo(new BigDecimal(0)) >= 0 ? discountPrice : new BigDecimal(0);
      carServiceRecord.setDiscountPrice(discountPrice);
      carServiceRecord.setCouponSource(couponEndUser.getCoupon().getSystemType());
      carServiceRecord.setPaymentType(PaymentType.COUPON);
      // couponEndUser.setIsUsed(true);
      // couponEndUserDao.merge(couponEndUser);
    }
    /**
     * 洗车券支付
     */
    else if (PaymentType.WASHCOUPON.equals(paymentType)) {
      carServiceRecord.setDiscountPrice(new BigDecimal(0));
    } else {
      carServiceRecord.setDiscountPrice(price);
    }

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


    if (PaymentType.WALLET.equals(paymentType)) {
      // BigDecimal walletMoney = new BigDecimal(0);
      // Boolean flag = true;
      // Wallet wallet = endUser.getWallet();
      AccountBalance accountBalance =
          accountBalanceService.getOfflineBalanceByTenant(endUser, carServiceRecord.getTenantID());
      if (accountBalance != null && accountBalance.getBalance().compareTo(new BigDecimal(0)) != 0) {
        if (couponEndUser != null) {
          carServiceRecord.setPaymentType(PaymentType.MIXCOUPONOFFLINE);
        } else {
          carServiceRecord.setPaymentType(PaymentType.OFFLINEBALLANCE);
        }
        if (accountBalance.getBalance().compareTo(carServiceRecord.getDiscountPrice()) >= 0) {// 混合余额支付，线下余额大于等于支付金额
          carServiceRecord.setOfflineBalance(carServiceRecord.getDiscountPrice());
          // accountBalance.setBalance(accountBalance.getBalance().subtract(
          // carServiceRecord.getDiscountPrice()));
          // flag = false;
        } else {// 混合余额支付，线下余额小于支付金额，剩余金额需普通余额支付,还需要支付的金额为walletMoney
          carServiceRecord.setOfflineBalance(accountBalance.getBalance());
          // walletMoney =
          // carServiceRecord.getDiscountPrice().subtract(accountBalance.getBalance());
          // accountBalance.setBalance(new BigDecimal(0));
        }
        // accountBalanceService.update(accountBalance);
      }

      // WalletRecord walletRecord = new WalletRecord();
      // walletRecord.setBalanceType(BalanceType.OUTCOME);
      // walletRecord.setWallet(wallet);
      // walletRecord.setWalletType(WalletType.MONEY);
      // walletRecord.setRemark(Message.success("csh.wallet.purService.record",
      // carServiceRecord.getCarService().getServiceName()).getContent());
      // walletRecord.setMoney(carServiceRecord.getDiscountPrice());
      // if (flag) {
      // if (walletMoney.compareTo(new BigDecimal(0)) > 0) {// 余额混合支付
      // wallet.setBalanceAmount(wallet.getBalanceAmount().subtract(walletMoney));
      // } else {// 普通余额支付
      // wallet.setBalanceAmount(wallet.getBalanceAmount().subtract(
      // carServiceRecord.getDiscountPrice()));
      // }
      // }
      // wallet.getWalletRecords().add(walletRecord);
      // walletDao.merge(wallet);

    }

    carServiceRecordDao.persist(carServiceRecord);

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
    if (PaymentType.WALLET.equals(carServiceRecord.getPaymentType())
        || PaymentType.MIXCOUPONOFFLINE.equals(carServiceRecord.getPaymentType())
        || PaymentType.OFFLINEBALLANCE.equals(carServiceRecord.getPaymentType())) {

      BigDecimal walletMoney = new BigDecimal(0);
      Boolean flag = true;
      EndUser endUser = carServiceRecord.getEndUser();
      Wallet wallet = endUser.getWallet();
      AccountBalance accountBalance =
          accountBalanceService.getOfflineBalanceByTenant(endUser, carServiceRecord.getTenantID());
      if (accountBalance != null && accountBalance.getBalance().compareTo(new BigDecimal(0)) != 0) {
        if (accountBalance.getBalance().compareTo(carServiceRecord.getDiscountPrice()) >= 0) {// 混合余额支付，线下余额大于等于支付金额
          accountBalance.setBalance(accountBalance.getBalance().subtract(
              carServiceRecord.getOfflineBalance()));
          flag = false;
        } else {// 混合余额支付，线下余额小于支付金额，剩余金额需普通余额支付,还需要支付的金额为walletMoney
          walletMoney = carServiceRecord.getDiscountPrice().subtract(accountBalance.getBalance());
          accountBalance.setBalance(new BigDecimal(0));
        }
        accountBalanceService.update(accountBalance);
      }

      WalletRecord walletRecord = new WalletRecord();
      walletRecord.setBalanceType(BalanceType.OUTCOME);
      walletRecord.setWallet(wallet);
      walletRecord.setWalletType(WalletType.MONEY);
      walletRecord.setRemark(Message.success("csh.wallet.purService.record",
          carServiceRecord.getCarService().getServiceName()).getContent());
      walletRecord.setMoney(carServiceRecord.getDiscountPrice());
      if (flag) {
        if (walletMoney.compareTo(new BigDecimal(0)) > 0) {// 余额混合支付
          wallet.setBalanceAmount(wallet.getBalanceAmount().subtract(walletMoney));
        } else {// 普通余额支付
          wallet.setBalanceAmount(wallet.getBalanceAmount().subtract(
              carServiceRecord.getDiscountPrice()));
        }
      }
      wallet.getWalletRecords().add(walletRecord);
      walletDao.merge(wallet);
    }

    // 消费兑换积分.规则 1元=1积分,不足1元送1积分(余额消费也送积分，因为余额充值时没有送了积分)
    if (carServiceRecord.getPaymentType() != null) {
      Wallet wallet = carServiceRecord.getEndUser().getWallet();
      WalletRecord walletRecord = new WalletRecord();
      walletRecord.setWallet(wallet);
      walletRecord.setBalanceType(BalanceType.INCOME);
      walletRecord.setWalletType(WalletType.SCORE);
      walletRecord.setScore(carServiceRecord.getPrice().setScale(0, BigDecimal.ROUND_UP));
      walletRecord.setRemark(Message.success("csh.wallet.score.comein.record").getContent());
      wallet.getWalletRecords().add(walletRecord);
      wallet.setScore(wallet.getScore().add(walletRecord.getScore())
          .setScale(0, BigDecimal.ROUND_UP));
      walletDao.merge(wallet);
    }

    if (carServiceRecord.getCouponEndUser() != null) {
      CouponEndUser couponEndUser = carServiceRecord.getCouponEndUser();
      couponEndUser.setIsUsed(true);
      couponEndUserDao.merge(couponEndUser);
    }

    carServiceRecordDao.merge(carServiceRecord);

    /**
     * 更新该服务购买次数
     */
    CarService carService = carServiceRecord.getCarService();
    carService.setPurchaseCounts(carService.getPurchaseCounts() + 1);
    carServiceDao.merge(carService);

    if (setting.getServiceCateWash().equals(
        carServiceRecord.getCarService().getServiceCategory().getId())) {
      if (PaymentType.WASHCOUPON.equals(carServiceRecord.getPaymentType())) {
        CarWashingCouponEndUser carWashingCoupon =
            carWashingCouponEndUserDao.userGetWashingCouponByTenant(carServiceRecord.getTenantID(),
                carServiceRecord.getEndUser());
        if (carWashingCoupon != null) {
          Integer remainNum = carWashingCoupon.getRemainNum() - 1;
          carWashingCoupon.setRemainNum(remainNum);
          if (remainNum == 0) {
            carWashingCoupon.setIsUsed(true);
          }
          carWashingCouponEndUserDao.merge(carWashingCoupon);
        }
      }
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
    return carServiceRecord;
  }

  @Override
  public CarServiceRecord updateServiceRecord(CarServiceRecord carServiceRecord,
      CouponEndUser couponEndUser) {
    PaymentType paymentType = carServiceRecord.getPaymentType();
    EndUser endUser = carServiceRecord.getEndUser();
    if (couponEndUser != null) {
      List<Filter> filters = new ArrayList<Filter>();
      Filter filter = new Filter("couponEndUser", Operator.eq, couponEndUser);
      filters.add(filter);
      List<CarServiceRecord> list = carServiceRecordDao.findList(null, null, filters, null);
      for (CarServiceRecord record : list) {
        record.setCouponEndUser(null);
        record.setCouponSource(null);
        record.setDiscountPrice(null);
        carServiceRecordDao.merge(record);
      }
      carServiceRecord.setCouponEndUser(couponEndUser);
      BigDecimal discountPrice =
          carServiceRecord.getPrice().subtract(couponEndUser.getCoupon().getAmount());
      discountPrice =
          discountPrice.compareTo(new BigDecimal(0)) >= 0 ? discountPrice : new BigDecimal(0);
      carServiceRecord.setDiscountPrice(discountPrice);
      carServiceRecord.setCouponSource(couponEndUser.getCoupon().getSystemType());
      carServiceRecord.setPaymentType(PaymentType.COUPON);
      // couponEndUser.setIsUsed(true);
      // couponEndUserDao.merge(couponEndUser);
    } else {
      carServiceRecord.setCouponSource(null);
      carServiceRecord.setCouponEndUser(null);
      carServiceRecord.setDiscountPrice(carServiceRecord.getPrice());
    }

    if (PaymentType.WALLET.equals(paymentType)) {
      // BigDecimal walletMoney = new BigDecimal(0);
      // Boolean flag = true;
      // Wallet wallet = endUser.getWallet();
      AccountBalance accountBalance =
          accountBalanceService.getOfflineBalanceByTenant(endUser, carServiceRecord.getTenantID());
      if (accountBalance != null && accountBalance.getBalance().compareTo(new BigDecimal(0)) != 0) {
        if (couponEndUser != null) {
          carServiceRecord.setPaymentType(PaymentType.MIXCOUPONOFFLINE);
        } else {
          carServiceRecord.setPaymentType(PaymentType.OFFLINEBALLANCE);
        }
        if (accountBalance.getBalance().compareTo(carServiceRecord.getDiscountPrice()) >= 0) {// 混合余额支付，线下余额大于等于支付金额
          carServiceRecord.setOfflineBalance(carServiceRecord.getDiscountPrice());
          // accountBalance.setBalance(accountBalance.getBalance().subtract(
          // carServiceRecord.getDiscountPrice()));
          // flag = false;
        } else {// 混合余额支付，线下余额小于支付金额，剩余金额需普通余额支付,还需要支付的金额为walletMoney
          carServiceRecord.setOfflineBalance(accountBalance.getBalance());
          // walletMoney =
          // carServiceRecord.getDiscountPrice().subtract(accountBalance.getBalance());
          // accountBalance.setBalance(new BigDecimal(0));
        }
        // accountBalanceService.update(accountBalance);
      }

      // WalletRecord walletRecord = new WalletRecord();
      // walletRecord.setBalanceType(BalanceType.OUTCOME);
      // walletRecord.setWallet(wallet);
      // walletRecord.setWalletType(WalletType.MONEY);
      // walletRecord.setRemark(Message.success("csh.wallet.purService.record",
      // carServiceRecord.getCarService().getServiceName()).getContent());
      // walletRecord.setMoney(carServiceRecord.getDiscountPrice());
      // if (flag) {
      // if (walletMoney.compareTo(new BigDecimal(0)) > 0) {// 余额混合支付
      // wallet.setBalanceAmount(wallet.getBalanceAmount().subtract(walletMoney));
      // } else {// 普通余额支付
      // wallet.setBalanceAmount(wallet.getBalanceAmount().subtract(
      // carServiceRecord.getDiscountPrice()));
      // }
      // }
      // wallet.getWalletRecords().add(walletRecord);
      // walletDao.merge(wallet);

    }

    carServiceRecordDao.merge(carServiceRecord);
    return carServiceRecord;
  }
}
