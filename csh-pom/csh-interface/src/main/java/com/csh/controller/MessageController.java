package com.csh.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.aspect.UserValidCheck;
import com.csh.beans.CommonAttributes;
import com.csh.beans.Message;
import com.csh.controller.base.MobileBaseController;
import com.csh.entity.EndUser;
import com.csh.entity.MessageInfo;
import com.csh.entity.MsgEndUser;
import com.csh.entity.commonenum.CommonEnum.MessageType;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.json.base.BaseRequest;
import com.csh.json.base.BaseResponse;
import com.csh.json.base.PageResponse;
import com.csh.json.base.ResponseMultiple;
import com.csh.json.request.MsgRequest;
import com.csh.service.EndUserService;
import com.csh.service.MessageInfoService;
import com.csh.service.MsgEndUserService;
import com.csh.utils.FieldFilterUtils;
import com.csh.utils.TokenGenerator;


/**
 * Controller - 消息中心
 * 
 * @author sujinxuan
 *
 */
@Controller("MessageController")
@RequestMapping("/message")
public class MessageController extends MobileBaseController {

  @Resource(name = "endUserServiceImpl")
  private EndUserService endUserService;

  @Resource(name = "messageInfoServiceImpl")
  private MessageInfoService messageInfoService;

  @Resource(name = "msgEndUserServiceImpl")
  private MsgEndUserService msgEndUserService;


  /**
   * 获取消息列表
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/getMsgList", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseMultiple<Map<String, Object>> getMsgList(@RequestBody BaseRequest req) {
    ResponseMultiple<Map<String, Object>> response = new ResponseMultiple<Map<String, Object>>();
    Integer pageSize = req.getPageSize();
    Integer pageNumber = req.getPageNumber();
    String token = req.getToken();
    Long userId = req.getUserId();
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    Pageable pageable = new Pageable();
    pageable.setPageNumber(pageNumber);
    pageable.setPageSize(pageSize);
    Page<MessageInfo> msgs = messageInfoService.findMsgByUser(userId, pageable);

    PageResponse pageInfo = new PageResponse();
    pageInfo.setPageNumber(pageNumber);
    pageInfo.setPageSize(pageSize);
    pageInfo.setTotal((int) msgs.getTotal());

    Integer count = 0; // 未读消息数
    EndUser endUser = endUserService.find(userId);
    if (endUser != null) {
      for (MsgEndUser msgEndUser : endUser.getMsgEndUsers()) {
        if (!msgEndUser.getIsRead()) {
          count++;
        }
      }
    }

    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    String[] propertys =
        {"id", "messageType", "messageTitle", "messageContent", "msgUser.createDate", "msgUser.id",
            "msgUser.isRead"};
    for (MessageInfo msg : msgs.getContent()) {
      Map<String, Object> msgMap = FieldFilterUtils.filterEntityMap(propertys, msg);
      result.add(msgMap);
    }
    response.setMsg(result);
    response.setPage(pageInfo);

    String newtoken = TokenGenerator.generateToken(token);
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setDesc(count.toString());
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }


  /**
   * 读取消息
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/readMessage", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody BaseResponse showMessage(@RequestBody MsgRequest req) {
    BaseResponse response = new BaseResponse();
    String token = req.getToken();
    Long userId = req.getUserId();
    Long msgId = req.getMsgId();

    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    EndUser endUser = endUserService.find(userId);
    MessageInfo msg = messageInfoService.find(msgId);
    MsgEndUser msgEndUser = msgEndUserService.findMsgEndUserByUserAndMsg(endUser, msg);
    msgEndUser.setIsRead(true);
    msgEndUserService.update(msgEndUser);
    String newtoken = TokenGenerator.generateToken(token);
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }


  /**
   * 清除消息
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/deleteMsgs", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody BaseResponse deleteMsgs(@RequestBody MsgRequest req) {
    BaseResponse response = new BaseResponse();

    String token = req.getToken();
    Long userId = req.getUserId();
    Long[] msgIds = req.getMsgIds();

    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    EndUser endUser = endUserService.find(userId);
    for (Long msgId : msgIds) {
      MessageInfo msg = messageInfoService.find(msgId);
      MsgEndUser msgEndUser = msgEndUserService.findMsgEndUserByUserAndMsg(endUser, msg);
      if (MessageType.PERSONALMSG.equals(msg.getMessageType())) {
        msgEndUserService.delete(msgEndUser);
        messageInfoService.delete(msg);
      } else if (MessageType.NEWSMSG.equals(msg.getMessageType())) {
        msgEndUserService.delete(msgEndUser);
      }
    }

    String newtoken = TokenGenerator.generateToken(token);
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }
}
