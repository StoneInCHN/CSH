package com.csh.service.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csh.beans.Message;
import com.csh.dao.AdvanceDepositsDao;
import com.csh.dao.EndUserDao;
import com.csh.dao.SystemConfigDao;
import com.csh.dao.WalletDao;
import com.csh.entity.EndUser;
import com.csh.entity.MessageInfo;
import com.csh.entity.MsgEndUser;
import com.csh.entity.SystemConfig;
import com.csh.entity.Wallet;
import com.csh.entity.WalletRecord;
import com.csh.entity.commonenum.CommonEnum.BalanceType;
import com.csh.entity.commonenum.CommonEnum.MessageType;
import com.csh.entity.commonenum.CommonEnum.SystemConfigKey;
import com.csh.entity.commonenum.CommonEnum.WalletType;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.MessageInfoService;
import com.csh.service.WalletService;

@Service("walletServiceImpl")
public class WalletServiceImpl extends BaseServiceImpl<Wallet, Long> implements WalletService {

  @Resource(name = "walletDaoImpl")
  private WalletDao walletDao;

  @Resource(name = "endUserDaoImpl")
  private EndUserDao endUserDao;

  @Resource(name = "advanceDepositsDaoImpl")
  private AdvanceDepositsDao advanceDepositsDao;

  @Resource(name = "systemConfigDaoImpl")
  private SystemConfigDao systemConfigDao;

  @Resource(name = "messageInfoServiceImpl")
  private MessageInfoService messageInfoService;


  @Resource(name = "walletDaoImpl")
  public void setBaseDao(WalletDao walletDao) {
    super.setBaseDao(walletDao);
  }

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void updateWallet(Long userId, BigDecimal amount, String recordNo) {
    EndUser endUser = endUserDao.find(userId);
    Wallet wallet = endUser.getWallet();
    wallet.setBalanceAmount(wallet.getBalanceAmount().add(amount));
    WalletRecord walletRecord = new WalletRecord();
    walletRecord.setBalanceType(BalanceType.INCOME);
    walletRecord.setWalletType(WalletType.MONEY);
    walletRecord.setMoney(amount);
    walletRecord.setRecordNo(recordNo);
    walletRecord.setRemark(Message.success("csh.wallet.chargeIn.record").getContent());
    walletRecord.setWallet(wallet);
    wallet.getWalletRecords().add(walletRecord);
    walletDao.merge(wallet);
  }

  @Override
  public Wallet giftRedPacket(Wallet wallet, SystemConfigKey systemConfigKey, String remark) {
    SystemConfig systemConfig = systemConfigDao.getConfigByKey(systemConfigKey, null);
    if (systemConfig != null) {
      WalletRecord walletRecord = new WalletRecord();
      walletRecord.setWallet(wallet);
      walletRecord.setBalanceType(BalanceType.INCOME);
      walletRecord.setWalletType(WalletType.REDPACKET);
      if (systemConfigKey.equals(SystemConfigKey.GROUTHFUND_DRIVING)) {
        String[] content = remark.split("_");
        if (content[0] != "") {
          BigDecimal drivingRedPacket =
              new BigDecimal(systemConfig.getConfigValue()).multiply(new BigDecimal(content[0]));
          walletRecord.setRedPacket(drivingRedPacket);
          walletRecord.setRemark(Message.success(content[1], drivingRedPacket).getContent());
        }
      } else {
        walletRecord.setRedPacket(new BigDecimal(systemConfig.getConfigValue()));
        walletRecord.setRemark(Message.success(remark, walletRecord.getRedPacket()).getContent());
      }
      wallet.getWalletRecords().add(walletRecord);
      wallet.setGiftAmount(wallet.getGiftAmount().add(walletRecord.getRedPacket()));
      walletDao.merge(wallet);

      MessageInfo msg = new MessageInfo();
      msg.setMessageType(MessageType.PERSONALMSG);
      msg.setMessageContent(walletRecord.getRemark());
      MsgEndUser msgEndUser = new MsgEndUser();
      msgEndUser.setEndUser(wallet.getEndUser());
      msgEndUser.setIsPush(false);
      msgEndUser.setIsRead(false);
      msgEndUser.setMessage(msg);
      msg.getMsgUser().add(msgEndUser);
      messageInfoService.save(msg);

      /**
       * 极光推送
       */
      messageInfoService.jpushMsg(msg);
    }
    return wallet;
  }


}
