package com.csh.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.csh.dao.MessageInfoDao;
import com.csh.entity.MessageInfo;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;

@Repository("messageInfoDaoImpl")
public class MessageInfoDaoImpl extends BaseDaoImpl<MessageInfo, Long> implements MessageInfoDao {

  @Override
  public Page<MessageInfo> findMsgByUser(Long userId, Pageable pageable) {
    if (userId == null) {
      return null;
    }
    try {
      String jpql =
          "select msg from MessageInfo AS msg inner join msg.msgUser as mm WHERE mm.endUser.id = :userId order by msg.createDate desc";
      Map<String, Object> paramMap = new HashMap<String, Object>();
      paramMap.put("userId", userId);
      return findPageCustomized(pageable, jpql, paramMap);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public List<MessageInfo> findMsgByUser(Long userId) {
    if (userId == null) {
      return null;
    }
    try {
      String jpql =
          "select msg from MessageInfo AS msg inner join msg.msgUser as mm WHERE mm.endUser.id = :userId order by msg.createDate desc";
      Map<String, Object> paramMap = new HashMap<String, Object>();
      paramMap.put("userId", userId);
      return findListCustomized(jpql, paramMap);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  @CachePut(value = "messageInfo", key = "'msg.content='+#deviceNo")
  public String createMsgInfoCache(String deviceNo, String msgContent) {
    return msgContent;
  }

  @Override
  @Cacheable(value = "messageInfo", key = "'msg.content='+#deviceNo")
  public String getMsgInfoCache(String deviceNo) {
    return null;
  }

  @Override
  @Cacheable(value = "messageInfo", key = "'msg.content='+#deviceNo")
  public void deleteMsgInfoCache(String deviceNo) {

  }
}
