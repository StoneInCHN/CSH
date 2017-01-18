package com.csh.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import cn.jpush.api.push.model.PushPayload;

import com.csh.common.log.LogUtil;
import com.csh.dao.MessageInfoDao;
import com.csh.dao.TenantAccountDao;
import com.csh.entity.MessageInfo;
import com.csh.entity.TenantAccount;
import com.csh.entity.commonenum.CommonEnum.AppPlatform;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.MessageInfoService;
import com.csh.utils.JPushUtil;

@Service("messageInfoServiceImpl")
public class MessageInfoServiceImpl extends BaseServiceImpl<MessageInfo, Long> implements
    MessageInfoService {

  @Resource(name = "tenantAccountDaoImpl")
  private TenantAccountDao tenantAccountDao;

  @Resource(name = "messageInfoDaoImpl")
  public void setBaseDao(MessageInfoDao messageInfoDao) {
    super.setBaseDao(messageInfoDao);
  }

  @Override
  public Page<MessageInfo> findMsgByTenantUser(Long userId, Pageable pageable) {
    return null;
  }

  @Override
  public void jpushMsgForTenant(MessageInfo messageInfo) {

    List<Filter> filters = new ArrayList<Filter>();
    Filter tenantFilter = new Filter("tenantID", Operator.eq, messageInfo.getTenantID());
    filters.add(tenantFilter);
    List<TenantAccount> accounts = tenantAccountDao.findList(null, null, filters, null);
    if (!CollectionUtils.isEmpty(accounts)) {
      List<String> iosPushRegIds = new ArrayList<String>();
      List<String> andoridPushRegIds = new ArrayList<String>();
      for (TenantAccount account : accounts) {
        if (account.getjpushRegId() != null) {
          AppPlatform appPlatform = tenantAccountDao.getTenantUserAppPlatform(account.getId());
          if (AppPlatform.ANDROID.equals(appPlatform)) {
            andoridPushRegIds.add(account.getjpushRegId());
          } else if (AppPlatform.IOS.equals(appPlatform)) {
            iosPushRegIds.add(account.getjpushRegId());
          }
        }
      }

      Map<String, String> map = new HashMap<String, String>();
      map.put("msgId", messageInfo.getId().toString());
      if (LogUtil.isDebugEnabled(MessageInfoServiceImpl.class)) {
        LogUtil
            .debug(
                MessageInfoServiceImpl.class,
                "jpush message for tenant app",
                "Push Message to tenant  with tenantId: %s,msgId: %s,msgContent: %s, iosRegJpushIdCounts: %s, androidRegJpushIdCounts: %s",
                messageInfo.getTenantID(), messageInfo.getId().toString(),
                messageInfo.getMessageContent(), iosPushRegIds.size(), andoridPushRegIds.size());
      }

      try {
        PushPayload payload = null;
        if (iosPushRegIds.size() > 0) {

          payload =
              JPushUtil.buildPushObject_ios_registerId(messageInfo.getMessageContent(), map,
                  iosPushRegIds.toArray(new String[iosPushRegIds.size()]));
          JPushUtil.sendPush(payload, null, null);
        }
        if (andoridPushRegIds.size() > 0) {
          payload =
              JPushUtil.buildPushObject_android_registerId(messageInfo.getMessageContent(), map,
                  andoridPushRegIds.toArray(new String[andoridPushRegIds.size()]));
          JPushUtil.sendPush(payload, null, null);
        }

      } catch (Exception e) {
        LogUtil.error(MessageInfoServiceImpl.class, "PushMsgForTenant", "Error:", e);
      } finally {
        iosPushRegIds = null;
        andoridPushRegIds = null;
        map = null;
      }
    }

  }
}
