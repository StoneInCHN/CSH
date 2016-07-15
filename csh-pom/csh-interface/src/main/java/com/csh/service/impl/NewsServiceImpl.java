package com.csh.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jpush.api.push.model.PushPayload;

import com.csh.common.log.LogUtil;
import com.csh.dao.AppDao;
import com.csh.dao.EndUserDao;
import com.csh.dao.NewsDao;
import com.csh.entity.App;
import com.csh.entity.EndUser;
import com.csh.entity.News;
import com.csh.entity.commonenum.CommonEnum.AppPlatform;
import com.csh.entity.commonenum.CommonEnum.MessageType;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.NewsService;
import com.csh.utils.JPushUtil;

@Service("newsServiceImpl")
public class NewsServiceImpl extends BaseServiceImpl<News, Long> implements NewsService {

  @Resource(name = "newsDaoImpl")
  private NewsDao newsDao;

  @Resource(name = "endUserDaoImpl")
  private EndUserDao endUserDao;

  @Resource(name = "appDaoImpl")
  private AppDao appDao;


  @Resource(name = "newsDaoImpl")
  public void setBaseDao(NewsDao newsDao) {
    super.setBaseDao(newsDao);
  }

  @Override
  public void jpushNews(Long newsId, String title, String contentUrl) {

    Map<String, String> map = new HashMap<String, String>();
    map.put("newsId", newsId.toString());
    map.put("contentUrl", contentUrl);
    map.put("type", MessageType.NEWSMSG.toString());

    List<EndUser> users = endUserDao.findList(null, null, null, null);
    for (EndUser user : users) {
      try {
        Long tenantId = null;
        if (user.getDefaultVehicle() != null) {
          tenantId = user.getDefaultVehicle().getTenantID();
        }
        AppPlatform appPlatform = endUserDao.getEndUserAppPlatform(user.getId());
        if (user.getjpushRegId() != null && appPlatform != null) {
          PushPayload payload = null;
          if (AppPlatform.ANDROID.equals(appPlatform)) {
            payload =
                JPushUtil.buildPushObject_android_registerId(title, map, user.getjpushRegId());
          } else if (AppPlatform.IOS.equals(appPlatform)) {
            payload = JPushUtil.buildPushObject_ios_registerId(title, map, user.getjpushRegId());
          }

          if (LogUtil.isDebugEnabled(MessageInfoServiceImpl.class)) {
            LogUtil
                .debug(
                    NewsServiceImpl.class,
                    "jpush news",
                    "Push News to All EndUser. newsId:%s, title: %s, contentUrl: %s, Tenant ID with User Belong: %s",
                    newsId, title, contentUrl, tenantId);
          }

          App app = appDao.getTenantAppById(tenantId);
          if (app != null) {
            JPushUtil.sendPush(payload, app.getJpushMasterSecret(), app.getJpushAppKey());
          } else {
            JPushUtil.sendPush(payload, null, null);
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

  }
}
