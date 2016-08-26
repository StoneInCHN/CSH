package com.csh.controller.estore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.aspect.UserValidCheck;
import com.csh.beans.CommonAttributes;
import com.csh.beans.Message;
import com.csh.common.log.LogUtil;
import com.csh.controller.base.MobileBaseController;
import com.csh.entity.Area;
import com.csh.entity.EndUser;
import com.csh.entity.estore.ReceiverAddress;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.ordering.Ordering.Direction;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.json.base.BaseResponse;
import com.csh.json.base.PageResponse;
import com.csh.json.base.ResponseMultiple;
import com.csh.json.base.ResponseOne;
import com.csh.json.request.ReceiverAddressRequest;
import com.csh.service.AreaService;
import com.csh.service.EndUserService;
import com.csh.service.ReceiverAddressService;
import com.csh.utils.FieldFilterUtils;
import com.csh.utils.TokenGenerator;


/**
 * Controller - 收货地址
 * 
 * @author sujinxuan
 *
 */
@Controller("receiverAddressController")
@RequestMapping("/estore/receiverAddress")
public class ReceiverAddressController extends MobileBaseController {

  @Resource(name = "endUserServiceImpl")
  private EndUserService endUserService;

  @Resource(name = "areaServiceImpl")
  private AreaService areaService;

  @Resource(name = "receiverAddressServiceImpl")
  private ReceiverAddressService receiverAddressService;


  /**
   * 收货地址列表
   * 
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseMultiple<Map<String, Object>> addressList(
      @RequestBody ReceiverAddressRequest request) {

    ResponseMultiple<Map<String, Object>> response = new ResponseMultiple<Map<String, Object>>();

    Long userId = request.getUserId();
    String token = request.getToken();
    Integer pageSize = request.getPageSize();
    Integer pageNumber = request.getPageNumber();

    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    EndUser endUser = endUserService.find(userId);
    List<Filter> filters = new ArrayList<Filter>();
    Filter userFilter = new Filter("member", Operator.eq, endUser.getId());
    filters.add(userFilter);

    Pageable pageable = new Pageable();
    pageable.setPageNumber(pageNumber);
    pageable.setPageSize(pageSize);
    pageable.setOrderProperty("isDefault");
    pageable.setOrderDirection(Direction.desc);
    Page<ReceiverAddress> addresses = receiverAddressService.findPage(pageable);
    String[] propertys = {"id", "consignee", "areaName", "address", "phone", "isDefault"};
    List<Map<String, Object>> result =
        FieldFilterUtils.filterCollectionMap(propertys, addresses.getContent());
    response.setMsg(result);

    PageResponse page = new PageResponse();
    page.setPageNumber(request.getPageNumber());
    page.setPageSize(request.getPageSize());
    page.setTotal((int) addresses.getTotal());
    response.setPage(page);

    String newtoken = TokenGenerator.generateToken(request.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }


  /**
   * 获取默认地址
   * 
   * @return
   */
  @RequestMapping(value = "/getDefault", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseOne<Map<String, Object>> getDefault(
      @RequestBody ReceiverAddressRequest request) {

    ResponseOne<Map<String, Object>> response = new ResponseOne<Map<String, Object>>();

    Long userId = request.getUserId();
    String token = request.getToken();

    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    EndUser endUser = endUserService.find(userId);
    List<Filter> filters = new ArrayList<Filter>();
    Filter userFilter = new Filter("member", Operator.eq, endUser.getId());
    Filter defaultFilter = new Filter("isDefault", Operator.eq, true);
    filters.add(userFilter);
    filters.add(defaultFilter);

    List<ReceiverAddress> list = receiverAddressService.findList(null, filters, null);
    Map<String, Object> result = null;
    if (!CollectionUtils.isEmpty(list)) {
      String[] propertys = {"id", "consignee", "areaName", "address", "phone", "isDefault"};
      result = FieldFilterUtils.filterEntityMap(propertys, list.get(0));
    }

    response.setMsg(result);
    String newtoken = TokenGenerator.generateToken(request.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }


  /**
   * 添加/修改 收货地址
   * 
   * @return
   */
  @RequestMapping(value = "/addOrEdit", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody BaseResponse add(@RequestBody ReceiverAddressRequest request) {

    BaseResponse response = new BaseResponse();

    Long userId = request.getUserId();
    String token = request.getToken();
    Long areaId = request.getAreaId();
    String consignee = request.getConsignee();
    String address = request.getAddress();
    String phone = request.getPhone();
    String zipCode = request.getZipCode();
    Boolean isDefault = request.getIsDefault();
    Long receiverId = request.getReceiverId();

    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    if (LogUtil.isDebugEnabled(ReceiverAddressController.class)) {
      LogUtil
          .debug(
              ReceiverAddressController.class,
              "addOrEdit",
              "add or Edit receiver address. receiverId: %s,areaId: %s,consignee: %s,address: %s，phone: %s,isDefault: %s",
              receiverId, areaId, consignee, address, phone, isDefault);
    }

    Area area = areaService.find(areaId);
    ReceiverAddress receiverAddress = receiverAddressService.find(receiverId);
    EndUser endUser = endUserService.find(userId);
    if (receiverAddress == null) {
      receiverAddress = new ReceiverAddress();
      receiverAddress.setMember(endUser);
    }

    receiverAddress.setArea(area);
    receiverAddress.setAreaName(area.getFullName());
    receiverAddress.setConsignee(consignee);
    receiverAddress.setPhone(phone);
    receiverAddress.setAddress(address);
    receiverAddress.setIsDefault(isDefault);
    receiverAddress.setZipCode(zipCode);
    receiverAddressService.addOrEditAddress(receiverAddress, endUser);

    String newtoken = TokenGenerator.generateToken(request.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }



  /**
   * 删除收货地址
   * 
   * @return
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody BaseResponse delete(@RequestBody ReceiverAddressRequest request) {

    BaseResponse response = new BaseResponse();

    Long userId = request.getUserId();
    String token = request.getToken();
    Long receiverId = request.getReceiverId();

    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }


    ReceiverAddress receiverAddress = receiverAddressService.find(receiverId);
    EndUser endUser = endUserService.find(userId);
    receiverAddressService.deleteAddress(receiverAddress, endUser);

    String newtoken = TokenGenerator.generateToken(request.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }

  /**
   * 获取省/市/区
   * 
   * @return
   */
  @RequestMapping(value = "/getArea", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseMultiple<Map<String, Object>> getArea(
      @RequestBody ReceiverAddressRequest request) {

    ResponseMultiple<Map<String, Object>> response = new ResponseMultiple<Map<String, Object>>();

    Long userId = request.getUserId();
    String token = request.getToken();
    Long areaId = request.getAreaId();

    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    List<Filter> filters = new ArrayList<Filter>();
    if (areaId != null) {
      Filter parentFilter = new Filter("parent", Operator.eq, areaId);
      filters.add(parentFilter);
    } else {
      Filter parentFilter = new Filter("parent", Operator.isNull, null);
      filters.add(parentFilter);
    }

    List<Area> list = areaService.findList(null, filters, null);
    String[] propertys = {"id", "name"};
    List<Map<String, Object>> result = FieldFilterUtils.filterCollectionMap(propertys, list);

    response.setMsg(result);
    String newtoken = TokenGenerator.generateToken(request.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }


}
