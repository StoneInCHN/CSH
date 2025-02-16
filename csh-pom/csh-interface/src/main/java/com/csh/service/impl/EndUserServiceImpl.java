package com.csh.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csh.dao.EndUserDao;
import com.csh.dao.ReportUserRegStatisticsDao;
import com.csh.entity.EndUser;
import com.csh.entity.ReportUserRegStatistics;
import com.csh.entity.Wallet;
import com.csh.entity.commonenum.CommonEnum.AccountStatus;
import com.csh.entity.commonenum.CommonEnum.AppPlatform;
import com.csh.entity.commonenum.CommonEnum.CouponSendType;
import com.csh.entity.commonenum.CommonEnum.SystemConfigKey;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.CouponService;
import com.csh.service.EndUserService;
import com.csh.service.WalletService;
import com.csh.utils.TimeUtils;

@Service("endUserServiceImpl")
public class EndUserServiceImpl extends BaseServiceImpl<EndUser, Long> implements EndUserService {

  @Resource(name = "endUserDaoImpl")
  private EndUserDao endUserDao;

  @Resource(name = "couponServiceImpl")
  private CouponService couponService;

  // @Resource(name = "advanceDepositsDaoImpl")
  // private AdvanceDepositsDao advanceDepositsDao;

  @Resource(name = "reportUserRegStatisticsDaoImpl")
  private ReportUserRegStatisticsDao reportUserRegStatisticsDao;

  @Resource(name = "walletServiceImpl")
  private WalletService walletService;

  @Resource(name = "endUserDaoImpl")
  public void setBaseDao(EndUserDao endUserDao) {
    super.setBaseDao(endUserDao);
  }

  @Transactional(readOnly = true)
  public EndUser findByUserName(String username) {
    return endUserDao.findByUserName(username);
  }


  @Transactional(readOnly = true)
  public EndUser findByUserMobile(String mobileNo) {
    return endUserDao.findByUserMobile(mobileNo);
  }

  @Override
  public String getEndUserToken(Long id) {
    // EndUser endUser = endUserDao.find(id);
    // if (AccountStatus.LOCKED.equals(endUser.getAccountStatus())
    // || AccountStatus.DELETE.equals(endUser.getAccountStatus())) {
    // if (LogUtil.isDebugEnabled(EndUserServiceImpl.class)) {
    // LogUtil.debug(EndUserServiceImpl.class, "check EndUser Account Status",
    // "The endUser is in disabled or delete status. endUser ID: %s, accountStatus: %s",
    // id.toString(), endUser.getAccountStatus().toString());
    // }
    // return null;
    // }
    return endUserDao.getEndUserToken(id);
  }


  @Override
  public String createEndUserToken(String token, Long id) {
    return endUserDao.createEndUserToken(token, id);
  }


  @Override
  public void deleteEndUserToken(Long id) {
    endUserDao.deleteEndUserToken(id);
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public EndUser userReg(String userName, String password) {
    EndUser regUser = new EndUser();
    regUser.setMobileNum(userName);
    regUser.setUserName(userName);
    regUser.setPassword(DigestUtils.md5Hex(password));
    regUser.setAccountStatus(AccountStatus.ACTIVED);
    Wallet wallet = new Wallet();
    wallet.setEndUser(regUser);
    regUser.setWallet(wallet);
    endUserDao.persist(regUser);

    ReportUserRegStatistics report =
        reportUserRegStatisticsDao.getReportByDate(TimeUtils.formatDate2Day(new Date()));
    if (report != null) {
      report.setRegNum(report.getRegNum() + 1);
      reportUserRegStatisticsDao.merge(report);
    } else {
      report = new ReportUserRegStatistics();
      report.setStatisticsDate(new Date());
      report.setRegNum(1);
      reportUserRegStatisticsDao.persist(report);
    }

    Boolean flag = couponService.takeCouponBySendType(null, regUser, CouponSendType.REG);
    regUser.setIsGetCoupon(flag);

    /**
     * 注册送基金红包
     */
    walletService.giftRedPacket(wallet, SystemConfigKey.GROUTHFUND_REG,
        "csh.wallet.reg.comein.redPacket");
    return regUser;
  }

  @Override
  public AppPlatform getEndUserAppPlatform(Long id) {
    return endUserDao.getEndUserAppPlatform(id);
  }

  @Override
  public AppPlatform createEndUserAppPlatform(AppPlatform appPlatform, Long id) {
    return endUserDao.createEndUserAppPlatform(appPlatform, id);
  }

}
