package com.csh.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.jpush.api.push.model.PushPayload;

import com.csh.dao.MessageInfoDao;
import com.csh.dao.MsgEndUserDao;
import com.csh.entity.EndUser;
import com.csh.entity.MessageInfo;
import com.csh.entity.MsgEndUser;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.MessageInfoService;
import com.csh.utils.JPushUtil;

@Service("messageInfoServiceImpl")
public class MessageInfoServiceImpl extends BaseServiceImpl<MessageInfo, Long> implements
    MessageInfoService {

  @Resource(name = "messageInfoDaoImpl")
  private MessageInfoDao messageInfoDao;

  @Resource(name = "msgEndUserDaoImpl")
  private MsgEndUserDao msgEndUserDao;

  @Resource(name = "messageInfoDaoImpl")
  public void setBaseDao(MessageInfoDao messageInfoDao) {
    super.setBaseDao(messageInfoDao);
  }

  @Override
  public Page<MessageInfo> findMsgByUser(Long userId, Pageable pageable) {
    return messageInfoDao.findMsgByUser(userId, pageable);
  }

  @Override
  public List<MessageInfo> findMsgByUser(Long userId) {
    return messageInfoDao.findMsgByUser(userId);
  }

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void jpushMsg(MessageInfo msg) {
    Integer unread_count = 0;
    for (MsgEndUser msgEndUser : msg.getMsgUser()) {
      if (!msgEndUser.getIsPush()) {
        msgEndUser.setIsPush(true);
        EndUser user = msgEndUser.getEndUser();
        String regId = user.getjpushRegId();
        // regIds.add(user.getjpushRegId());
        msgEndUserDao.merge(msgEndUser);
        for (MsgEndUser msgUser : user.getMsgEndUsers()) {
          if (!msgUser.getIsRead()) {
            unread_count++;
          }
        }

        Map<String, String> map = new HashMap<String, String>();
        map.put("msgId", msg.getId().toString());
        map.put("title", msg.getMessageTitle());
        map.put("content", msg.getMessageContent());
        map.put("time", String.valueOf(msgEndUser.getModifyDate().getTime()));
        map.put("unreadCount", unread_count.toString());
        try {
          PushPayload payload =
              JPushUtil.buildPushObject_android_registerId(msg.getMessageContent(), map, regId);
          JPushUtil.sendPush(payload);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

    }

  }
}
