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
import com.csh.common.log.LogUtil;
import com.csh.controller.base.MobileBaseController;
import com.csh.entity.CarService;
import com.csh.entity.CarServiceRecord;
import com.csh.entity.EndUser;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.ordering.Ordering;
import com.csh.framework.ordering.Ordering.Direction;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.json.base.BaseResponse;
import com.csh.json.base.PageResponse;
import com.csh.json.base.ResponseMultiple;
import com.csh.json.request.CarServiceRequest;
import com.csh.service.CarServiceRecordService;
import com.csh.service.CarServiceService;
import com.csh.service.EndUserService;
import com.csh.utils.FieldFilterUtils;
import com.csh.utils.TokenGenerator;


@Controller("CarServiceController")
@RequestMapping("/carService")
public class CarServiceController extends MobileBaseController {


  @Resource(name = "endUserServiceImpl")
  private EndUserService endUserService;

  @Resource(name = "carServiceRecordServiceImpl")
  private CarServiceRecordService carServiceRecordService;

  @Resource(name = "carServiceServiceImpl")
  private CarServiceService carServiceService;


  /**
   * 用户直接购买汽车服务或预约
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/buyService", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody BaseResponse buyService(@RequestBody CarServiceRequest serviceReq) {

    BaseResponse response = new BaseResponse();
    Long userId = serviceReq.getUserId();
    String token = serviceReq.getToken();
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    EndUser endUser = endUserService.find(userId);
    CarService carService = carServiceService.find(serviceReq.getServiceId());

    CarServiceRecord carServiceRecord = new CarServiceRecord();
    carServiceRecord.setTenantID(carService.getTenantInfo().getId());
    carServiceRecord.setCarService(carService);
    carServiceRecord.setEndUser(endUser);
    carServiceRecord.setChargeStatus(serviceReq.getChargeStatus());
    if (serviceReq.getPaymentType() != null) {
      carServiceRecord.setPaymentType(serviceReq.getPaymentType());
    }
    carServiceRecord.setPrice(serviceReq.getPrice());
    carServiceRecord.setTenantName(carService.getTenantInfo().getTenantName());
    carServiceRecordService.save(carServiceRecord);
    if (LogUtil.isDebugEnabled(CarServiceController.class)) {
      LogUtil
          .debug(
              CarServiceController.class,
              "Save",
              "User buy Car Service. UserName: %s, Tenant: %s, Service: %s, price: %s, paymentType: %s, chargeStatus: %s",
              endUser.getUserName(), carServiceRecord.getTenantName(), carService.getServiceName(),
              carServiceRecord.getPrice(), carServiceRecord.getPaymentType(),
              carServiceRecord.getChargeStatus());
    }

    String newtoken = TokenGenerator.generateToken(serviceReq.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }

  /**
   * 用户支付汽车服务
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/payService", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody BaseResponse payService(@RequestBody CarServiceRequest serviceReq) {

    BaseResponse response = new BaseResponse();
    Long userId = serviceReq.getUserId();
    String token = serviceReq.getToken();
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }


    CarServiceRecord carServiceRecord = carServiceRecordService.find(serviceReq.getRecordId());
    carServiceRecord.setChargeStatus(serviceReq.getChargeStatus());
    carServiceRecord.setPaymentType(serviceReq.getPaymentType());
    // carServiceRecord.setPrice(serviceReq.getPrice());
    carServiceRecordService.update(carServiceRecord);
    if (LogUtil.isDebugEnabled(CarServiceController.class)) {
      LogUtil
          .debug(
              CarServiceController.class,
              "Update",
              "User Pay for Car Service. UserName: %s, Tenant: %s, Service: %s, price: %s, paymentType: %s, chargeStatus: %s",
              carServiceRecord.getEndUser().getUserName(), carServiceRecord.getTenantName(),
              carServiceRecord.getCarService().getServiceName(), carServiceRecord.getPrice(),
              carServiceRecord.getPaymentType(), carServiceRecord.getChargeStatus());
    }

    String newtoken = TokenGenerator.generateToken(serviceReq.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }


  /**
   * 用户购买汽车服务列表
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/purchaseList", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseMultiple<Map<String, Object>> purchaseList(
      @RequestBody CarServiceRequest serviceReq) {

    ResponseMultiple<Map<String, Object>> response = new ResponseMultiple<Map<String, Object>>();
    Long userId = serviceReq.getUserId();
    String token = serviceReq.getToken();
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }


    EndUser endUser = endUserService.find(userId);

    List<Filter> filters = new ArrayList<Filter>();
    Filter filter = new Filter("endUser", Operator.eq, endUser);
    filters.add(filter);

    List<Ordering> orderings = new ArrayList<Ordering>();
    Ordering ordering = new Ordering("createDate", Direction.desc);
    orderings.add(ordering);
    Pageable pageable = new Pageable(serviceReq.getPageNumber(), serviceReq.getPageSize());
    pageable.setFilters(filters);
    pageable.setOrders(orderings);
    Page<CarServiceRecord> records = carServiceRecordService.findPage(pageable);

    PageResponse page = new PageResponse();
    page.setPageNumber(serviceReq.getPageNumber());
    page.setPageSize(serviceReq.getPageSize());
    page.setTotal((int) records.getTotal());
    response.setPage(page);

    String[] properties =
        {"createDate", "chargeStatus", "price", "carService.serviceName", "tenantName"};
    List<Map<String, Object>> map =
        FieldFilterUtils.filterCollectionMap(properties, records.getContent());
    response.setMsg(map);

    String newtoken = TokenGenerator.generateToken(serviceReq.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }

}
