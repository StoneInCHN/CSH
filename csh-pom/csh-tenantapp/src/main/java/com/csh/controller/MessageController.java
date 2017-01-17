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
import com.csh.entity.MessageInfo;
import com.csh.entity.commonenum.CommonEnum.TenantMsgType;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.json.base.BaseRequest;
import com.csh.json.base.PageResponse;
import com.csh.json.base.ResponseMultiple;
import com.csh.json.base.ResponseOne;
import com.csh.json.request.WarnMsgRequest;
import com.csh.service.DeviceInfoService;
import com.csh.service.MessageInfoService;
import com.csh.service.MsgEndUserService;
import com.csh.service.TenantAccountService;
import com.csh.service.VehicleService;
import com.csh.service.WalletService;
import com.csh.utils.FieldFilterUtils;
import com.csh.utils.LatLonUtil;
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

  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  @Resource(name = "deviceInfoServiceImpl")
  private DeviceInfoService deviceInfoService;

  @Resource(name = "messageInfoServiceImpl")
  private MessageInfoService messageInfoService;

  @Resource(name = "msgEndUserServiceImpl")
  private MsgEndUserService msgEndUserService;

  @Resource(name = "walletServiceImpl")
  private WalletService walletService;

  @Resource(name = "vehicleServiceImpl")
  private VehicleService vehicleService;

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
    String userToken = tenantAccountService.getTenantUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    Pageable pageable = new Pageable();
    pageable.setPageNumber(pageNumber);
    pageable.setPageSize(pageSize);
    List<Filter> filters = new ArrayList<Filter>();

    List<TenantMsgType> types = new ArrayList<TenantMsgType>();
    // 非法震动，非法启动，超速不用返回给租户app
    types.add(TenantMsgType.OBDWARN);
    types.add(TenantMsgType.WATERTEMPWARN);
    types.add(TenantMsgType.CRASHWARN);
    types.add(TenantMsgType.ROLLOVERWARN);
    types.add(TenantMsgType.BATTERYREMOVEWARN);
    types.add(TenantMsgType.LOWVOLTAGEWARN);
    types.add(TenantMsgType.FAULTCODEWARN);
    Filter tenantMsgTypeFilter = new Filter("tenantMsgType", Operator.in, types);
    filters.add(tenantMsgTypeFilter);
    pageable.setFilters(filters);
    Page<MessageInfo> msgs = messageInfoService.findPage(pageable, true, req.getTenantId());

    PageResponse pageInfo = new PageResponse();
    pageInfo.setPageNumber(pageNumber);
    pageInfo.setPageSize(pageSize);
    pageInfo.setTotal((int) msgs.getTotal());

    // Integer count = 0; // 未读消息数
    // EndUser endUser = tenantAccountService.find(userId);
    // if (endUser != null) {
    // for (MsgEndUser msgEndUser : endUser.getMsgEndUsers()) {
    // if (!msgEndUser.getIsRead()) {
    // count++;
    // }
    // }
    // }

    String[] propertys = {"id", "messageContent", "tenantMsgType", "createDate", "lat", "lon"};
    List<Map<String, Object>> result =
        FieldFilterUtils.filterCollectionMap(propertys, msgs.getContent());
    for (Map<String, Object> map : result) {
      map.put("alarmPlace",
          LatLonUtil.convertCoorForAddr((String) map.get("lat"), (String) map.get("lon")));
    }

    response.setMsg(result);
    response.setPage(pageInfo);

    String newtoken = TokenGenerator.generateToken(token);
    tenantAccountService.createTenantUserToken(newtoken, userId);
    response.setToken(newtoken);
    // response.setDesc(count.toString());
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }

  /**
   * 读取消息
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/showMessage", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseOne<Map<String, Object>> showMessage(@RequestBody BaseRequest req) {
    ResponseOne<Map<String, Object>> response = new ResponseOne<Map<String, Object>>();
    String token = req.getToken();
    Long userId = req.getUserId();
    Long msgId = req.getEntityId();

    // 验证登录token
    String userToken = tenantAccountService.getTenantUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    // TenantUser tenantUser = tenantAccountService.find(userId);
    MessageInfo msg = messageInfoService.find(msgId);
    String[] propertys = {"id", "messageContent", "tenantMsgType", "createDate", "lat", "lon"};
    Map<String, Object> map = FieldFilterUtils.filterEntityMap(propertys, msg);

    map.put("alarmPlace",
        LatLonUtil.convertCoorForAddr((String) map.get("lat"), (String) map.get("lon")));
    response.setMsg(map);

    String newtoken = TokenGenerator.generateToken(token);
    tenantAccountService.createTenantUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }

  /**
   * 接受警告消息
   * 
   * @return
   */
  @RequestMapping(value = "/pushVehicleWainingInfo", method = RequestMethod.POST)
  public @ResponseBody String pushVehicleWainingInfo(
      @RequestBody List<WarnMsgRequest> msgRequestList) {
    for (WarnMsgRequest request : msgRequestList) {
      // vehicleService.
    }

    return "success";
  }
}
