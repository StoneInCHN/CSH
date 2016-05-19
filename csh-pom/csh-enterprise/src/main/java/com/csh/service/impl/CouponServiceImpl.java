package com.csh.service.impl; 

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
import com.csh.service.TenantInfoService;
import com.csh.utils.ApiUtils;
import com.csh.utils.SettingUtils;

@Service("couponServiceImpl")
public class CouponServiceImpl extends BaseServiceImpl<Coupon,Long> implements CouponService {

      @Resource(name ="tenantInfoServiceImpl")
      private TenantInfoService tenantInfoService;
      @Resource(name="couponDaoImpl")
      public void setBaseDao(CouponDao couponDao) {
         super.setBaseDao(couponDao);
      }

      @Override
      @Transactional(propagation = Propagation.REQUIRED)
      public void saveCoupon (Coupon coupon)
      {
         Long tenantID =tenantAccountService.getCurrentTenantID ();
         List<EndUser> endUserList =  tenantInfoService.findEndUser ();
         Set<MsgEndUser> msgEndUserList = new HashSet <MsgEndUser> ();
         for (EndUser endUser : endUserList)
        {
           MsgEndUser msgEndUser = new MsgEndUser ();
           msgEndUser.setEndUser (endUser);
           msgEndUser.setIsRead (false);
           msgEndUser.setIsPush (false);
           msgEndUserList.add (msgEndUser);
        }
         this.save (coupon,true);
         MessageInfo msgInfo = new MessageInfo ();
         msgInfo.setMessageType (MessageType.PROMOTION);
         msgInfo.setMessageContent ("优惠券发送，赶快前往活动中心领取");
         msgInfo.setSendType (SendType.PUSH);
         msgInfo.setTenantID (tenantID);
         msgInfo.setMsgUser (msgEndUserList);
         
         Map<String, Object> params = new HashMap<String, Object>();
         params.put ("msgId", msgInfo.getId ());
         Setting setting = SettingUtils.get();
         ApiUtils.post (setting.getMsgPushUrl ());
      }
}