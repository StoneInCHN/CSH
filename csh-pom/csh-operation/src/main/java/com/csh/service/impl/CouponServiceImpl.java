package com.csh.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.beans.Setting;
import com.csh.dao.CouponDao;
import com.csh.entity.Coupon;
import com.csh.entity.EndUser;
import com.csh.entity.MessageInfo;
import com.csh.entity.MsgEndUser;
import com.csh.entity.commonenum.CommonEnum.MessageType;
import com.csh.entity.commonenum.CommonEnum.SendType;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.CouponService;
import com.csh.service.EndUserService;
import com.csh.service.MessageInfoService;
import com.csh.service.MsgEndUserService;
import com.csh.utils.ApiUtils;
import com.csh.utils.JsonUtil;
import com.csh.utils.SettingUtils;
import com.csh.utils.SpringUtils;

@Service("couponServiceImpl")
public class CouponServiceImpl extends BaseServiceImpl<Coupon, Long> implements CouponService {

  @Resource(name = "couponDaoImpl")
  private CouponDao couponDao;

  @Resource(name = "messageInfoServiceImpl")
  private MessageInfoService messageInfoService;

  @Resource(name = "endUserServiceImpl")
  private EndUserService endUserService;

  @Resource(name = "msgEndUserServiceImpl")
  private MsgEndUserService msgEndUserService;
  
  @Resource(name = "couponDaoImpl")
  public void setBaseDao(CouponDao couponDao) {
    super.setBaseDao(couponDao);
  }

  @Override
  public void save(Coupon coupon) {
    if (coupon.getIsEnabled()) {
      coupon.setIsSendout(true);
    } else {
      coupon.setIsSendout(false);
    }
    couponDao.persist(coupon);
    if (coupon.getIsEnabled()) {
      MessageInfo msgInfo = new MessageInfo();
      msgInfo.setMessageTitle(SpringUtils.getMessage("csh.coupon.name"));
      msgInfo.setMessageType(MessageType.PROMOTION);
      msgInfo.setMessageContent(SpringUtils.getMessage("csh.coupon.send"));
      msgInfo.setSendType(SendType.PUSH);
      messageInfoService.save(msgInfo);
      List<EndUser> endUserList = endUserService.findAll();
      Set<MsgEndUser> msgEndUserList = new HashSet<MsgEndUser>();
      for (EndUser endUser : endUserList) {
        MsgEndUser msgEndUser = new MsgEndUser();
        msgEndUser.setEndUser(endUser);
        msgEndUser.setIsRead(false);
        msgEndUser.setIsPush(false);
        msgEndUser.setMessage(msgInfo);
        msgEndUserService.save(msgEndUser);
        msgEndUserList.add(msgEndUser);
      }
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("msgId", msgInfo.getId());
      Setting setting = SettingUtils.get();
      String data =JsonUtil.getJsonString4JavaPOJO(params);
      ApiUtils.post(setting.getMsgPushUrl(),data);
    }
  }

  @Override
  public Coupon update(Coupon coupon) {
    if (coupon.getIsEnabled() && !coupon.getIsSendout()) {
      MessageInfo msgInfo = new MessageInfo();
      msgInfo.setMessageTitle(SpringUtils.getMessage("csh.coupon.name"));
      msgInfo.setMessageType(MessageType.PROMOTION);
      msgInfo.setMessageContent(SpringUtils.getMessage("csh.coupon.send"));
      msgInfo.setSendType(SendType.PUSH);
      messageInfoService.save(msgInfo);
      List<EndUser> endUserList = endUserService.findAll();
      Set<MsgEndUser> msgEndUserList = new HashSet<MsgEndUser>();
      for (EndUser endUser : endUserList) {
        MsgEndUser msgEndUser = new MsgEndUser();
        msgEndUser.setEndUser(endUser);
        msgEndUser.setIsRead(false);
        msgEndUser.setIsPush(false);
        msgEndUserService.save(msgEndUser);
        msgEndUserList.add(msgEndUser);
      }
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("msgId", msgInfo.getId());
      Setting setting = SettingUtils.get();
      String data =JsonUtil.getJsonString4JavaPOJO(params);
      ApiUtils.post(setting.getMsgPushUrl(),data);
      
      coupon.setIsSendout(true);
    }
    return couponDao.merge(coupon);
  }

  
  

}
