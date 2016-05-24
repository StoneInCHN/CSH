package com.csh.service.impl; 

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csh.dao.CouponDao;
import com.csh.entity.Coupon;
import com.csh.entity.EndUser;
import com.csh.entity.MessageInfo;
import com.csh.entity.MsgEndUser;
import com.csh.entity.commonenum.CommonEnum.MessageType;
import com.csh.entity.commonenum.CommonEnum.SendType;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.CouponService;
import com.csh.service.MessageInfoService;
import com.csh.service.MsgEndUserService;
import com.csh.service.TenantInfoService;

@Service("couponServiceImpl")
public class CouponServiceImpl extends BaseServiceImpl<Coupon,Long> implements CouponService {

      @Resource(name ="tenantInfoServiceImpl")
      private TenantInfoService tenantInfoService;
      @Resource(name ="messageInfoServiceImpl")
      private MessageInfoService messageInfoService;
      @Resource(name ="msgEndUserServiceImpl")
      private MsgEndUserService msgEndUserService;
      @Resource(name="couponDaoImpl")
      public void setBaseDao(CouponDao couponDao) {
         super.setBaseDao(couponDao);
      }

      @Override
      @Transactional(propagation = Propagation.REQUIRED)
      public MessageInfo saveCoupon (Coupon coupon)
      {
        if (coupon.getIsEnabled ())
        {
          Long tenantID =tenantAccountService.getCurrentTenantID ();
          List<EndUser> endUserList =  tenantInfoService.findEndUser ();
          Set<MsgEndUser> msgEndUserList = new HashSet <MsgEndUser> ();
          
          MessageInfo msgInfo = new MessageInfo ();
          msgInfo.setMessageTitle ("优惠券");
          
          msgInfo.setMessageType (MessageType.PROMOTION);
          msgInfo.setMessageContent ("优惠券发送，赶快前往活动中心领取");
          msgInfo.setSendType (SendType.PUSH);
          msgInfo.setTenantID (tenantID);
//          msgInfo.setMsgUser (msgEndUserList);
          for (EndUser endUser : endUserList)
         {
            MsgEndUser msgEndUser = new MsgEndUser ();
            msgEndUser.setEndUser (endUser);
            msgEndUser.setIsRead (false);
            msgEndUser.setIsPush (false);
            msgEndUser.setMessage (msgInfo);
            msgEndUserService.save (msgEndUser);
            msgEndUserList.add (msgEndUser);
         }
          coupon.setIsSendout (true);
          this.save (coupon,true);
          
          messageInfoService.save (msgInfo);
          return msgInfo;
        }else {
          coupon.setIsSendout (false);
          this.save (coupon,true);
          return null;
        }
         
         
         
      }

      @Override
      public MessageInfo updateCoupon (Coupon coupon)
      {
        if (coupon.getIsEnabled () && !coupon.getIsSendout ())
        {
          Long tenantID =tenantAccountService.getCurrentTenantID ();
          List<EndUser> endUserList =  tenantInfoService.findEndUser ();
          Set<MsgEndUser> msgEndUserList = new HashSet <MsgEndUser> ();
          
          MessageInfo msgInfo = new MessageInfo ();
          msgInfo.setMessageTitle ("优惠券");
          
          msgInfo.setMessageType (MessageType.PROMOTION);
          msgInfo.setMessageContent ("优惠券发送，赶快前往活动中心领取");
          msgInfo.setSendType (SendType.PUSH);
          msgInfo.setTenantID (tenantID);
//          msgInfo.setMsgUser (msgEndUserList);
          for (EndUser endUser : endUserList)
         {
            MsgEndUser msgEndUser = new MsgEndUser ();
            msgEndUser.setEndUser (endUser);
            msgEndUser.setIsRead (false);
            msgEndUser.setIsPush (false);
            msgEndUser.setMessage (msgInfo);
            msgEndUserService.save (msgEndUser);
            msgEndUserList.add (msgEndUser);
         }
          coupon.setIsSendout (true);
          this.update (coupon, "createDate", "tenantID","tenantName");
          
          messageInfoService.save (msgInfo);
          return msgInfo;
        }else {
          this.update (coupon, "createDate", "tenantID","tenantName");
          return null;
        }
      }
}